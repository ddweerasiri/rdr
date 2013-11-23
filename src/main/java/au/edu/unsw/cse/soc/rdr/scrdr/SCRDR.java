package au.edu.unsw.cse.soc.rdr.scrdr;
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

import au.edu.unsw.cse.soc.rdr.core.*;
import org.apache.log4j.Logger;

/**
 * User: denis
 * SC-RDR Tree
 */
public class SCRDR {
    static Logger log = Logger.getLogger(SCRDR.class);

    private SCRDRRootNode rootNode;

    public SCRDRRootNode getRootNode() {
        return rootNode;
    }

    public SCRDR() {
        RDRBasedRule rule = new RDRBasedRule("1=1", "unknown");
        this.rootNode = new SCRDRRootNode(rule);
    }

    public boolean insertRule(Case theCase, RDRBasedRule rule, Long parentNodeID) {
        RDRNode newNode = new SCRDRNode(rule);
        insertNode(theCase, newNode, parentNodeID);
        rule.addMetaData(parentNodeID, theCase.toString(), newNode.getDepth());
        return true;
    }

    private boolean insertNode(Case theCase, RDRNode newRDRNode, Long parentNodeID) {
        //search parent Node id
        RDRNode parentNode = this.rootNode.getNodeByID(parentNodeID);

        //insert the node to that parent node
        parentNode.insertChildNode(theCase, newRDRNode);

        return true;
    }

    public void printRuleTree() {
        System.out.println(this.rootNode.toString());
    }
}
