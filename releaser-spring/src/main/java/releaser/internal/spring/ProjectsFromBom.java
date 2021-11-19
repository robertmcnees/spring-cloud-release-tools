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

package releaser.internal.spring;

import releaser.internal.project.ProjectVersion;
import releaser.internal.project.Projects;

/**
 * Class representing this and other projects retrieved from the BOM.
 */
public class ProjectsFromBom {

	/**
	 * All projects with versions taken from BOM.
	 */
	public final Projects allProjectVersionsFromBom;

	/**
	 * Current project retrieved from BOM.
	 */
	public final ProjectVersion currentProjectFromBom;

	public ProjectsFromBom(Projects allProjectVersionsFromBom, ProjectVersion currentProjectFromBom) {
		this.allProjectVersionsFromBom = allProjectVersionsFromBom;
		this.currentProjectFromBom = currentProjectFromBom;
	}

	public ProjectsFromBom(Projects allProjectVersionsFromBom) {
		this(allProjectVersionsFromBom, null);
	}

}
