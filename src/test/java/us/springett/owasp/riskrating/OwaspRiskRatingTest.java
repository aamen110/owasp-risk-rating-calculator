/*
 * This file is part of the OWASP Risk Rating Calculator.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package us.springett.owasp.riskrating;

import org.junit.Assert;
import org.junit.Test;
import us.springett.owasp.riskrating.factors.BusinessImpactFactor;
import us.springett.owasp.riskrating.factors.TechnicalImpactFactor;
import us.springett.owasp.riskrating.factors.ThreatAgentFactor;
import us.springett.owasp.riskrating.factors.VulnerabilityFactor;

public class OwaspRiskRatingTest {

    @Test
    public void calculationTest() throws Exception {
        OwaspRiskRating riskRating = new OwaspRiskRating()
                .with(ThreatAgentFactor.SkillLevel.ADVANCED_COMPUTER_USER)
                .with(ThreatAgentFactor.Motive.POSSIBLE_REWARD)
                .with(ThreatAgentFactor.Opportunity.SOME_ACCESS_OR_RESOURCES_REQUIRED)
                .with(ThreatAgentFactor.Size.AUTHENTICATED_USERS)
                .with(VulnerabilityFactor.EaseOfDiscovery.DIFFICULT)
                .with(VulnerabilityFactor.EaseOfExploit.THEORETICAL)
                .with(VulnerabilityFactor.Awareness.HIDDEN)
                .with(VulnerabilityFactor.IntrusionDetection.NOT_LOGGED)
                .with(TechnicalImpactFactor.LossOfConfidentiality.ALL_DATA_DISCLOSED)
                .with(TechnicalImpactFactor.LossOfIntegrity.EXTENSIVE_SERIOUSLY_CORRUPT_DATA)
                .with(TechnicalImpactFactor.LossOfAvailability.MINIMAL_SECONDARY_SERVICES_INTERRUPTED)
                .with(TechnicalImpactFactor.LossOfAccountability.COMPLETELY_ANONYMOUS)
                .with(BusinessImpactFactor.FinancialDamage.SIGNIFICANT_EFFECT_ON_ANNUAL_PROFIT)
                .with(BusinessImpactFactor.ReputationDamage.LOSS_OF_MAJOR_ACCOUNTS)
                .with(BusinessImpactFactor.NonCompliance.HIGH_PROFILE_VIOLATION)
                .with(BusinessImpactFactor.PrivacyViolation.MILLIONS_OF_PEOPLE);
        Score score = riskRating.calculateScore();
        Level likelihood = score.getLikelihood();
        Level technicalImpact = score.getTechnicalImpact();
        Level businessImact = score.getBusinessImpact();

        Assert.assertEquals(4.875, score.getLikelihoodScore(), 0);
        Assert.assertEquals(6.5, score.getTechnicalImpactScore(), 0);
        Assert.assertEquals(6.75, score.getBusinessImpactScore(), 0);

        Assert.assertEquals(Level.MEDIUM, score.getLikelihood());
        Assert.assertEquals(Level.HIGH, score.getTechnicalImpact());
        Assert.assertEquals(Level.HIGH, score.getBusinessImpact());
    }
}
