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

import au.edu.unsw.cse.soc.rdr.core.Case;
import au.edu.unsw.cse.soc.rdr.core.RDRNode;
import org.apache.log4j.Logger;

/**
 * User: denis
 * TODO: Include the class description here
 */
public class SCRDRRootNode extends SCRDRNode {
    static Logger log = Logger.getLogger(SCRDRRootNode.class);

    public SCRDRRootNode(RDRBasedRule defaultRule) {
        super(defaultRule);
        depth = 0;
    }

    public boolean insertChildNode(Case theCase, RDRNode newNode) {
        if (theCase != Case.TRUENODE) {
            log.error("Only TrueNode is accepted for a Root node");
            return false;
        } else {
            super.insertChildNode(theCase, newNode);
            return true;
        }
    }
}
