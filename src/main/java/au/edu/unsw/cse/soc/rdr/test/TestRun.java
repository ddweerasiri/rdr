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

import au.edu.unsw.cse.soc.rdr.core.Case;
import au.edu.unsw.cse.soc.rdr.scrdr.RDRBasedRule;
import au.edu.unsw.cse.soc.rdr.scrdr.SCRDR;
import org.apache.log4j.Logger;

/**
 * User: denis
 * TODO: Include the class description here
 */
public class TestRun {
    static Logger log = Logger.getLogger(TestRun.class);

    public static void main(String[] args) {
        SCRDR rdrTree1 = new SCRDR();
        SCRDR rdrTree2 = new SCRDR();

        RDRBasedRule rule1 = new RDRBasedRule(new TaskCategory("development=\"java\""), new CloudResourceModel("modeilID=A001"));
        RDRBasedRule rule2 = new RDRBasedRule(new TaskCategory("development=\"python\""), new CloudResourceModel("modeilID=B001"));
        RDRBasedRule rule3 = new RDRBasedRule(new TaskCategory("development=\"java web application dev.\""), new CloudResourceModel("modeilID=C001"));
        RDRBasedRule rule4 = new RDRBasedRule(new TaskCategory("development=\"java web application dev in Tomcat.\""), new CloudResourceModel("modeilID=D001"));
        RDRBasedRule rule7 = new RDRBasedRule(new TaskCategory("development=\"java web application dev in GlassFish.\""), new CloudResourceModel("modeilID=F001"));
        RDRBasedRule rule5 = new RDRBasedRule(new TaskCategory("development=\"java web application dev in Tomcat-v1.\""), new CloudResourceModel("modeilID=E001"));

        rdrTree1.insertRule(Case.TRUENODE, rule1, rdrTree1.getRootNode().getID());
        rdrTree1.insertRule(Case.FALSENODE, rule2, rdrTree1.getRootNode().getIfTrueNode().getID());
        rdrTree1.insertRule(Case.TRUENODE, rule3, rdrTree1.getRootNode().getIfTrueNode().getID());
        rdrTree1.insertRule(Case.TRUENODE, rule4, rdrTree1.getRootNode().getIfTrueNode().getIfTrueNode().getID());
        rdrTree1.insertRule(Case.FALSENODE, rule7, rdrTree1.getRootNode().getIfTrueNode().getIfTrueNode().getID());
        rdrTree1.insertRule(Case.TRUENODE, rule5, rdrTree1.getRootNode().getIfTrueNode().getIfTrueNode().getIfTrueNode().getID());

        RDRBasedRule rule6 = new RDRBasedRule(new DeploymentContext(new CloudResourceModel("modeilID=A001"),
                new DeploymentScenario("deploymentScenario=Public")), new ConcreteConfigurationArtifact("concreteArtifactID=X001"));

        rdrTree2.insertRule(Case.TRUENODE, rule6, rdrTree2.getRootNode().getID());

        rdrTree1.printRuleTree();
        rdrTree2.printRuleTree();

    }
}
