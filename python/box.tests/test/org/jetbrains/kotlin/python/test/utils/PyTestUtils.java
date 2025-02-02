/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.python.test.utils;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public final class PyTestUtils {

    private PyTestUtils() {
    }

    @NotNull
    public static List<String> getFilesInDirectoryByExtension(@NotNull String directory, String extension) {
        File dir = new File(directory);

        if (!dir.isDirectory()) return ContainerUtil.emptyList();

        List<File> files = FileUtil.findFilesByMask(Pattern.compile(".*\\." + extension + "$"), dir);

        return ContainerUtil.map2List(files, File::getAbsolutePath);
    }
}
