/*
 * SonarQube Java
 * Copyright (C) 2012-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.checks.tests;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.java.checks.verifier.TestUtils.nonCompilingTestSourcesPath;
import static org.sonar.java.checks.verifier.TestUtils.testSourcesPath;

class TooManyAssertionsCheckTest {
  @Test
  void detected() {
    assertThat(new TooManyAssertionsCheck().maximum).isEqualTo(25);
  }

  @Test
  void custom_at_2() {
    TooManyAssertionsCheck check = new TooManyAssertionsCheck();
    check.maximum = 2;
    CheckVerifier.newVerifier()
      .onFile(testSourcesPath("checks/TooManyAssertionsCheckCustom2.java"))
      .withCheck(check)
      .verifyIssues();
  }

  @Test
  void custom_at_25() {
    TooManyAssertionsCheck check = new TooManyAssertionsCheck();
    check.maximum = 25;
    CheckVerifier.newVerifier()
      .onFile(testSourcesPath("checks/TooManyAssertionsCheckCustom25.java"))
      .withCheck(check)
      .verifyIssues();
  }

  @Test
  void nonCompiling() {
    CheckVerifier.newVerifier()
      .onFile(nonCompilingTestSourcesPath("checks/TooManyAssertionsCheck.java"))
      .withCheck(new TooManyAssertionsCheck())
      .verifyNoIssues();
  }

}
