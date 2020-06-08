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
package com.futurewei.alcor.dataplane.service.impl;

import com.futurewei.alcor.common.message.MessageClient;
import com.futurewei.alcor.dataplane.config.Config;
import com.futurewei.alcor.dataplane.config.grpc.GoalStateProvisionerClient;
import com.futurewei.alcor.dataplane.service.GoalStateService;
import com.futurewei.alcor.schema.Goalstate;
import com.futurewei.alcor.schema.Goalstateprovisioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OVSGoalStateServiceImpl implements GoalStateService {

    @Autowired
    private Config config;
    public Goalstate.GoalState getGoalState() {
        return goalState;
    }

    public void setGoalState(Goalstate.GoalState goalState) {
        this.goalState = goalState;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isFastPath() {
        return isFastPath;
    }

    public void setFastPath(boolean fastPath) {
        isFastPath = fastPath;
    }

    private Goalstate.GoalState goalState;
    private String ip;
    private boolean isFastPath = false;


    public MessageClient getKafkaClient() {
        return kafkaClient;
    }

    public void setKafkaClient(MessageClient kafkaClient) {
        this.kafkaClient = kafkaClient;
    }

    GoalStateProvisionerClient gRpcClientForEpHost = null;
    MessageClient kafkaClient = null;

    @Override
    public List<Goalstateprovisioner.GoalStateOperationReply.GoalStateOperationStatus>
    SendGoalStateToHosts() {

        if (isFastPath) {
            return new GoalStateProvisionerClient(ip, Config.port).
                    PushNetworkResourceStates(goalState);
        } else {
            String topicForEndpoint = Config.PRODUCER_CLIENT_ID + ip;
            getKafkaClient().runProducer(topicForEndpoint, goalState);
            return null;
        }
    }
}
