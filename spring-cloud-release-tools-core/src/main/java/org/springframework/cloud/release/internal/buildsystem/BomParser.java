/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.release.internal.buildsystem;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.release.internal.ReleaserProperties;

/**
 * Parses the poms for a given project and populates versions from a release train.
 *
 * @author Marcin Grzejszczak
 */
class BomParser {

	private static final Logger log = LoggerFactory.getLogger(BomParser.class);

	private final File thisProjectRoot;

	private final String thisTrainBomLocation;

	private final Pattern versionPattern;

	private final ReleaserProperties properties;

	BomParser(ReleaserProperties properties, File thisProjectRoot) {
		this.thisProjectRoot = thisProjectRoot;
		this.thisTrainBomLocation = properties.getPom().getThisTrainBom();
		this.versionPattern = Pattern.compile(properties.getPom().getBomVersionPattern());
		this.properties = properties;
	}

	// the BOM contains all versions of projects and its parent MUST be Spring Cloud
	// Dependencies Parent
	VersionsFromBom versionsFromBom() {
		Model model = PomReader.pom(thisProjectRoot, this.thisTrainBomLocation);
		if (model == null) {
			return VersionsFromBom.EMPTY_VERSION;
		}
		Set<Project> projects = model.getProperties().entrySet().stream()
				.filter(propertyMatchesVersionPattern()).map(toProject())
				.collect(Collectors.toSet());
		String releaseTrainProjectVersion = model.getVersion();
		projects.add(
				new Project(this.properties.getMetaRelease().getReleaseTrainProjectName(),
						releaseTrainProjectVersion));
		VersionsFromBom bomVersions = new VersionsFromBom(this.properties, projects);
		return new VersionsFromBom(properties, bomVersions, customVersions(projects));
	}

	private VersionsFromBom customVersions(Set<Project> projects) {
		CustomBomParser parser = CustomBomParser.parser(this.thisProjectRoot,
				this.properties, projects);
		return parser.parseBom(this.thisProjectRoot, this.properties);
	}

	private Predicate<Map.Entry<Object, Object>> propertyMatchesVersionPattern() {
		return entry -> this.versionPattern.matcher(entry.getKey().toString()).matches();
	}

	private Function<Map.Entry<Object, Object>, Project> toProject() {
		return entry -> {
			Matcher matcher = this.versionPattern.matcher(entry.getKey().toString());
			// you have to first match to get info about the group
			matcher.matches();
			String name = matcher.group(1);
			return new Project(name, entry.getValue().toString());
		};
	}

}