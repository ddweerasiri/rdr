package au.edu.unsw.cse.soc.rdr.test;
/*
 * Copyright (c) 2013, Denis Weerasiri All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.log4j.Logger;

/**
 * User: denis
 * TODO: Include the class description here
 */
public class DeploymentContext {
    static Logger log = Logger.getLogger(DeploymentContext.class);

    DeploymentScenario scenario;
    CloudResourceModel cloudResourceModel;

    public DeploymentContext(CloudResourceModel cloudResourceModel, DeploymentScenario scenario) {
        this.scenario = scenario;
        this.cloudResourceModel = cloudResourceModel;
    }

    public String toString() {
        return this.scenario.toString() + " AND " + this.cloudResourceModel.toString();
    }

}
