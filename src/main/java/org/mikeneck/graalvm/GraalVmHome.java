/*
 * Copyright 2019 Shinya Mochida
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * Distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mikeneck.graalvm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

class GraalVmHome {

    private final Path graalVmHome;

    GraalVmHome(Path home) {
        graalVmHome = home;
    }

    boolean notFound() {
        return !exists();
    }

    boolean exists() {
        return Files.exists(graalVmHome);
    }

    @NotNull
    Optional<Path> graalVmUpdater() {
        return graalVmUpdaterCandidates()
                .stream()
                .filter(Files::exists)
                .findFirst();
    }

    private List<Path> graalVmUpdaterCandidates() {
        return Arrays.asList(
                graalVmHome.resolve("bin/gu"),
                graalVmHome.resolve("bin/gu.cmd")
        );
    }

    @NotNull
    Optional<Path> nativeImage() {
        return nativeImageCandidates()
                .stream()
                .filter(Files::exists)
                .findFirst();
    }

    private List<Path> nativeImageCandidates() {
        return Arrays.asList(
                graalVmHome.resolve("bin/native-image"),
                graalVmHome.resolve("bin/native-image.cmd")
        );
    }

    @NotNull
    Optional<Path> javaExecutable() {
        return javaExecutableCandidates()
                .stream()
                .filter(Files::exists)
                .findFirst();
    }

    private List<Path> javaExecutableCandidates() {
        return Arrays.asList(
                graalVmHome.resolve("bin/java"),
                graalVmHome.resolve("bin/java.exe")
        );
    }

    @Override
    public String toString() {
        return graalVmHome.toString();
    }
}
