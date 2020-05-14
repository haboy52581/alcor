/*
Copyright 2019 The Alcor Authors.

Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

package com.futurewei.alcor.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.futurewei.alcor.common.entity.CustomerResource;
import lombok.Data;

import java.util.List;

@Data
public class VpcWebRequestObject extends CustomerResource {

    @JsonProperty("cidr")
    private String cidr;

    @JsonProperty("routes")
    private List<RouteWebObject> routes;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    @JsonProperty("dns_domain")
    private String dnsDomain;

    @JsonProperty("mtu")
    private Integer mtu;

    @JsonProperty("port_security_enabled")
    private boolean portSecurityEnabled;

    @JsonProperty("provider:network_type")
    private String networkType;

    @JsonProperty("provider:physical_network")
    private String physicalNetwork;

    @JsonProperty("provider:segmentation_id")
    private Integer segmentationId;

    @JsonProperty("router:external")
    private boolean routerExternal;

    @JsonProperty("segments")
    private List<SegmentWebRequestObject> segments;

    @JsonProperty("shared")
    private boolean shared;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("vlan_transparent")
    private boolean vlanTransparent;

    @JsonProperty("is_default")
    private boolean isDefault;

    @JsonProperty("availability_zone_hints")
    private List availabilityZoneHints;

    @JsonProperty("revision_number")
    private Integer revisionNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("tags-any")
    private String tagsAny;

    @JsonProperty("not-tags")
    private String notTags;

    @JsonProperty("not-tags-any")
    private String notTagsAny;

    @JsonProperty("fields")
    private String fields;

    @JsonProperty("sort_dir")
    private String sortDir;

    @JsonProperty("sort_key")
    private String sortKey;

    public VpcWebRequestObject() {
    }

    public VpcWebRequestObject(String projectId, String id, String name, List<RouteWebObject> routeWebObjectList) {
        this(projectId, id, name, null, routeWebObjectList);
    }

    public VpcWebRequestObject(VpcWebRequestObject state) {
        this(state.getProjectId(), state.getId(), state.getName(), state.getDescription(), state.getRoutes());
    }

    public VpcWebRequestObject(String projectId, String id, String name, String description, List<RouteWebObject> routeWebObjectList) {

        super(projectId, id, name, description);
        this.routes = routeWebObjectList;
    }
}

