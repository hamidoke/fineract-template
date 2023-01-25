/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.portfolio.savings.domain;

import java.util.List;
import org.apache.fineract.portfolio.savings.data.GSIMContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GSIMContainerRepository extends JpaRepository<GSIMContainer, Long>, JpaSpecificationExecutor<GSIMContainer> {

    @Query(value = "SELECT distinct(cl.id) AS id,cl.display_name AS name,cl.office_id AS officeId FROM m_client cl INNER JOIN m_group_client mgc on cl.id = mgc.client_id  INNER JOIN  m_group mg on mgc.group_id = mg.id  INNER  JOIN m_savings_account msa on cl.id = msa.client_id  INNER JOIN gsim_accounts ga on msa.gsim_id = ga.id  WHERE mg.id  = ?1 AND cl.display_name  LIKE %"
            + "?2" + "% AND ga.account_number = ?3", nativeQuery = true)
    List<GSIMContainer> findGsimAccountContainerbyGsimAccountIdAndName(@Param("groupId") Long groupId, @Param("name") String name,
            @Param("parentGSIMAccountNo") String parentGSIMAccountNo);
}
