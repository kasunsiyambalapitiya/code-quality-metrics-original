/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.wso2.code.quality.matrices.model;

import java.io.Serializable;

/**
 * This is a bean class used to set the json structures for calling the github graphql API
 *
 * @since 1.0.0
 */
public class Graphql implements Serializable {
    private String graphqlInputWithHistory;
    private String graphqlInputWithoutHistory;

    public Graphql() {

    }

    public String getGraphqlInputWithHistory() {
        return graphqlInputWithHistory;
    }

    public void setGraphqlInputWithHistory(String owner, String repositoryName, String commitHash, String fileName) {
        this.graphqlInputWithHistory = "{repository(owner:\"" + owner + "\",name:\"" + repositoryName + "\")" +
                "{object(expression:\"" + commitHash + "\"){ ... on Commit{blame(path:\"" + fileName + "\")" +
                "{ranges{startingLine endingLine age commit{history(first: 2) { edges { node {  message url } } } " +
                "author { name email } } } } } } } }";
    }

    public String getGraphqlInputWithoutHistory() {
        return graphqlInputWithoutHistory;
    }

    public void setGraphqlInputWithoutHistory(String owner, String repositoryName, String
            parentCommitHashForCallingGraphQl, String fileName) {
        this.graphqlInputWithoutHistory = "{repository(owner:\"" + owner + "\",name:\"" + repositoryName + "\")" +
                "{object(expression:\"" + parentCommitHashForCallingGraphQl + "\"){ ... on Commit{blame(path:\"" + fileName + "\"){ranges{startingLine endingLine age commit{ url author { name email } } } } } } } }";
    }
}