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

package org.springframework.cloud.release.internal.sagan;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Marcin Grzejszczak
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Release {

	public String releaseStatus = "";

	public String refDocUrl = "";

	public String apiDocUrl = "";

	public String groupId = "";

	public String artifactId = "";

	public Repository repository;

	public String version = "";

	public boolean current;

	public boolean generalAvailability;

	public boolean preRelease;

	public String versionDisplayName = "";

	public boolean snapshot;

	@Override
	public String toString() {
		return "Release{" + "releaseStatus='" + this.releaseStatus + '\''
				+ ", refDocUrl='" + this.refDocUrl + '\'' + ", apiDocUrl='"
				+ this.apiDocUrl + '\'' + ", groupId='" + this.groupId + '\''
				+ ", artifactId='" + this.artifactId + '\'' + ", repository="
				+ this.repository + ", version='" + this.version + '\'' + ", current="
				+ this.current + ", generalAvailability=" + this.generalAvailability
				+ ", preRelease=" + this.preRelease + ", versionDisplayName='"
				+ this.versionDisplayName + '\'' + ", snapshot=" + this.snapshot + '}';
	}

}
