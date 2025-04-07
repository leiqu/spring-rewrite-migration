/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.springframework.samples.petclinic.struct;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single Structured Object.
 *
 * Note that there are multiple levels of validation for EntityJSON.
 *
 * There is a generic Structured Object schema which all Structured Objects must conform to.
 *
 * There is an EntityJSON schema which describes a single JSON object where each element
 * is a Structured Object. This is the object which is submitted with a single message and
 * is simply a collection of named objects which the message may refer to.
 *
 * There are individual schemas for specific Structured Object types, but there is no
 * requirement for such a schema to exist. Message originators are allowed to emit messages
 * containing objects which conform only to the generic Structured Object schema which, aside
 * from the mandatory fields of all Structured objects (type ID and type version) and the
 * constraint that if an id attribute is present that it must be an array of (idType, idValue)
 * tuples, may contain any valid JSON.
 *
 * @author Bruce Skingle
 *
 */
public class StructuredObject
{
    private final Object                     instanceSource_;
    private final ObjectNode                 jsonNode_;
    private final String                     toString;
    private final String                     type;
    private final String                     version;
    private final int                        majorVersion;
    private final int                        minorVersion;




    /* package */ StructuredObject(Object instanceSource, ObjectNode jsonNode)
    {
        instanceSource_ = instanceSource;
        jsonNode_ = jsonNode;

        type = jsonNode.get("type").asText();
        version = jsonNode.get("version").asText();

        String[] parts = version.split("\\.");
        majorVersion = Integer.parseInt(parts[0]);
        minorVersion = Integer.parseInt(parts[1]);

        StringBuffer s = new StringBuffer("StructuredObject(\"");

        s.append(type);
        s.append(" v");
        s.append(version);
        s.append("\"");

        boolean first = true;
        JsonNode idNode = jsonNode.get("id");
        s.append(")");
        toString = s.toString();
    }

    /**
     * Return the parse context from which this object was created, which includes error reports
     * if the input was invalid in some way.
     *
     * @return The parse context from which this object was created.
     */



    /**
     * @return The type of this object.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return The Type Version of this object, guaranteed to be a string of the form
     * [0-9]+.[0-9]+
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @return The first (major) element of the type version.
     */
    public int getMajorVersion()
    {
        return majorVersion;
    }

    /**
     * @return The second (minor) element of the type version.
     */
    public int getMinorVersion()
    {
        return minorVersion;
    }

    /**
     * @return The immutable list of ID objects for this object.
     */

    @Override
    public String toString()
    {
        return toString;
    }
}
