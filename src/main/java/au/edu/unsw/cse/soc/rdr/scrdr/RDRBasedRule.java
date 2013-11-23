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

import au.edu.unsw.cse.soc.rdr.core.Rule;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * User: denis
 * Rule implementation based on RDR
 */
public class RDRBasedRule implements Rule {
    static Logger log = Logger.getLogger(RDRBasedRule.class);

    private Object context;
    private Object conclusion;
    private Long id;

    //MetaData
    private String relationshipType = null;
    private int depth = -1;
    private Long parentNodeID = null;
    //MetaData

    public RDRBasedRule(Object context, Object conclusion) {
        id = UUID.randomUUID().getMostSignificantBits();
        this.context = context;
        this.conclusion = conclusion;
    }


    @Override
    public Long getID() {
        return id;
    }

    @Override
    public Object getContext() {
        return this.context;
    }

    @Override
    public Object getConclusion() {
        return this.conclusion;
    }

    @Override
    public String toString(){
        //return "if " + "\"" + context + "\" then \"" + conclusion + "\" : node:id:" + id + ", depth:" + depth + ", parent-id:" + parentNodeID + ", relationshipType:" + relationshipType;
        return "if " + "\"" + context + "\" then \"" + conclusion + "\"";
    }

    public void addMetaData(Long parentNodeID, String relationType, int depth) {
        this.parentNodeID = parentNodeID;
        this.relationshipType = relationType;
        this.depth = depth;
    }
}
