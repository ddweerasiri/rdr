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
import au.edu.unsw.cse.soc.rdr.core.Rule;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * User: denis
 * TODO: Include the class description here
 */
public class SCRDRNode implements RDRNode {
    static Logger log = Logger.getLogger(SCRDRNode.class);

    private Long id;
    private RDRBasedRule rule;
    protected SCRDRNode ifTrueNode = null;
    protected SCRDRNode ifFalseNode = null;
    protected int depth;

    public SCRDRNode getIfTrueNode() {
        return ifTrueNode;
    }

    public SCRDRNode getIfFalseNode() {
        return ifFalseNode;
    }

    public SCRDRNode(RDRBasedRule rule) {
        this.id = rule.getID();
        this.rule = rule;
    }

    @Override
    public boolean insertChildNode(Case theCase, RDRNode newNode) {
        if(theCase.equals(Case.TRUENODE) && newNode != null && ifTrueNode == null) {
            ifTrueNode = (SCRDRNode) newNode;
            ifTrueNode.depth = this.depth + 1;
            return true;
        } else if((theCase.equals(Case.FALSENODE) && newNode != null && ifFalseNode == null)) {
            ifFalseNode = (SCRDRNode) newNode;
            ifFalseNode.depth = this.depth + 1;
            return true;
        } else {
            log.error("Error occurred.");
            if(newNode == null) {
                log.error("NewNode is null");
            } else if (ifTrueNode != null) {
                log.error("TrueNode is already registered");
            } else if (ifFalseNode != null) {
                log.error("FalseNode is already registered");
            }
            return false;
        }

    }


    @Override
    public Long getID() {
        return this.id;
    }

    @Override
    public Rule getRule() {
        return this.rule;
    }

    @Override
    public boolean setRule(Rule r) {
        this.rule = (RDRBasedRule) r;
        return true;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    public RDRNode getNodeByID(Long nodeID) {
        if(this.getID().equals(nodeID)) {
            return this;
        } else if (ifTrueNode != null) {
            if (ifTrueNode.getID().equals(nodeID)) {
                return ifTrueNode;
            } else {
                RDRNode trueNode = ifTrueNode.getNodeByID(nodeID);
                if (trueNode != null) {
                    return trueNode;
                } else {
                    log.info("No node found for given ID:" + nodeID);
                    return null;
                }
            }

        } else if (ifFalseNode != null && ifFalseNode.getID().equals(nodeID)) {
            if (ifFalseNode.getID().equals(nodeID)) {
                return ifFalseNode;
            } else {
                RDRNode falseNode = ifFalseNode.getNodeByID(nodeID);
                if (falseNode != null) {
                    return falseNode;
                } else {
                    log.info("No node found for given ID:" + nodeID);
                    return null;
                }
            }
        }
        /*else if (ifTrueNode == null || ifFalseNode == null) {
            log.info("No node found for given ID:" + nodeID);
            return null;
        }*/
        else {
            //RDRNode trueNode = ifTrueNode.getNodeByID(nodeID);
            //RDRNode falseNode = ifFalseNode.getNodeByID(nodeID);
            /*if (trueNode != null) {
                return trueNode;
            } else if (falseNode != null) {
                return falseNode;
            } else if (trueNode != null && falseNode != null) {
                String error = "ID contains in multiple places.";
                RuntimeException ex = new RuntimeException(error);
                log.error(error, ex);
                throw ex;
            } else {*/
                String error = "Unknown error occurred during tree search.";
                RuntimeException ex = new RuntimeException(error);
                log.error(error, ex);
                throw ex;

        }
    }

    @Override
    public String toString() {
        String string = "";
        if (this instanceof SCRDRRootNode) {
            string += this.getRule().toString() + "\n";
        }
        if (ifTrueNode != null) {
            String arrow =constructArrow(ifTrueNode);

            string += arrow + " " + ifTrueNode.getRule().toString() + "\n";
            string += ifTrueNode.toString();
        }
        if (ifFalseNode != null) {
            String arrow =constructArrow(ifFalseNode);

            string += arrow + " " + ifFalseNode.getRule().toString() + "\n";
            string += ifFalseNode.toString();
        }

        return string;
    }

    private String constructArrow(SCRDRNode node) {
        String arrow = "";
        for (int i = 1; i <= node.getDepth(); i++){
            arrow += "-";
            if(i == node.getDepth()) {
                arrow += ">";
            }
        }
        return arrow;
    }
}
