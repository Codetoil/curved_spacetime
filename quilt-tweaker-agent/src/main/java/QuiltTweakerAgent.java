/**
 * Curved Spacetime is an easy-to-use modular simulator for General Relativity.<br>
 * Copyright (C) 2023  Anthony Michalek (Codetoil)<br>
 * Copyright 2016 FabricMC<br>
 * <br>
 * This file is part of Curved Spacetime<br>
 * <br>
 * This program is free software: you can redistribute it and/or modify <br>
 * it under the terms of the GNU General Public License as published by <br>
 * the Free Software Foundation, either version 3 of the License, or <br>
 * (at your option) any later version.<br>
 * <br>
 * This program is distributed in the hope that it will be useful,<br>
 * but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 * GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License<br>
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.jar.JarFile;

public class QuiltTweakerAgent {
    public static void premain(String agentArgs, Instrumentation inst) throws IOException {
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        Arrays.stream(classpathEntries)
                .filter((classPathEntry) -> classPathEntry.contains("asm"))
                .forEach((classPathEntry) ->
                {
                    try {
                        inst.appendToBootstrapClassLoaderSearch(new JarFile(classPathEntry));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        inst.addTransformer(
                new ClassFileTransformer() {
                    @Override
                    public byte[] transform(ClassLoader loader,
                                            String className,
                                            Class<?> classBeingRedefined,
                                            ProtectionDomain protectionDomain,
                                            byte[] classfileBuffer) throws IllegalClassFormatException {
                        if (className.equals("net/fabricmc/api/EnvType"))
                        {
                            return new byte[] {
                                    (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x34, (byte) 0x00, (byte) 0x34, (byte) 0x09, (byte) 0x00,
                                    (byte) 0x04, (byte) 0x00, (byte) 0x27, (byte) 0x0A, (byte) 0x00, (byte) 0x28,
                                    (byte) 0x00, (byte) 0x29, (byte) 0x07, (byte) 0x00, (byte) 0x14, (byte) 0x07,
                                    (byte) 0x00, (byte) 0x2A, (byte) 0x0A, (byte) 0x00, (byte) 0x0E, (byte) 0x00,
                                    (byte) 0x2B, (byte) 0x0A, (byte) 0x00, (byte) 0x0E, (byte) 0x00, (byte) 0x2C,
                                    (byte) 0x08, (byte) 0x00, (byte) 0x0F, (byte) 0x0A, (byte) 0x00, (byte) 0x04,
                                    (byte) 0x00, (byte) 0x2C, (byte) 0x09, (byte) 0x00, (byte) 0x04, (byte) 0x00,
                                    (byte) 0x2D, (byte) 0x08, (byte) 0x00, (byte) 0x11, (byte) 0x09, (byte) 0x00,
                                    (byte) 0x04, (byte) 0x00, (byte) 0x2E, (byte) 0x08, (byte) 0x00, (byte) 0x12,
                                    (byte) 0x09, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x2F, (byte) 0x07,
                                    (byte) 0x00, (byte) 0x30, (byte) 0x01, (byte) 0x00, (byte) 0x06, (byte) 0x43,
                                    (byte) 0x4C, (byte) 0x49, (byte) 0x45, (byte) 0x4E, (byte) 0x54, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x1A, (byte) 0x4C, (byte) 0x6E, (byte) 0x65, (byte) 0x74,
                                    (byte) 0x2F, (byte) 0x66, (byte) 0x61, (byte) 0x62, (byte) 0x72, (byte) 0x69,
                                    (byte) 0x63, (byte) 0x6D, (byte) 0x63, (byte) 0x2F, (byte) 0x61, (byte) 0x70,
                                    (byte) 0x69, (byte) 0x2F, (byte) 0x45, (byte) 0x6E, (byte) 0x76, (byte) 0x54,
                                    (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x3B, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x06, (byte) 0x53, (byte) 0x45, (byte) 0x52, (byte) 0x56, (byte) 0x45,
                                    (byte) 0x52, (byte) 0x01, (byte) 0x00, (byte) 0x10, (byte) 0x43, (byte) 0x55,
                                    (byte) 0x52, (byte) 0x56, (byte) 0x45, (byte) 0x44, (byte) 0x5F, (byte) 0x53,
                                    (byte) 0x50, (byte) 0x41, (byte) 0x43, (byte) 0x45, (byte) 0x54, (byte) 0x49,
                                    (byte) 0x4D, (byte) 0x45, (byte) 0x01, (byte) 0x00, (byte) 0x07, (byte) 0x24,
                                    (byte) 0x56, (byte) 0x41, (byte) 0x4C, (byte) 0x55, (byte) 0x45, (byte) 0x53,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x1B, (byte) 0x5B, (byte) 0x4C, (byte) 0x6E,
                                    (byte) 0x65, (byte) 0x74, (byte) 0x2F, (byte) 0x66, (byte) 0x61, (byte) 0x62,
                                    (byte) 0x72, (byte) 0x69, (byte) 0x63, (byte) 0x6D, (byte) 0x63, (byte) 0x2F,
                                    (byte) 0x61, (byte) 0x70, (byte) 0x69, (byte) 0x2F, (byte) 0x45, (byte) 0x6E,
                                    (byte) 0x76, (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x3B,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x06, (byte) 0x76, (byte) 0x61, (byte) 0x6C,
                                    (byte) 0x75, (byte) 0x65, (byte) 0x73, (byte) 0x01, (byte) 0x00, (byte) 0x1D,
                                    (byte) 0x28, (byte) 0x29, (byte) 0x5B, (byte) 0x4C, (byte) 0x6E, (byte) 0x65,
                                    (byte) 0x74, (byte) 0x2F, (byte) 0x66, (byte) 0x61, (byte) 0x62, (byte) 0x72,
                                    (byte) 0x69, (byte) 0x63, (byte) 0x6D, (byte) 0x63, (byte) 0x2F, (byte) 0x61,
                                    (byte) 0x70, (byte) 0x69, (byte) 0x2F, (byte) 0x45, (byte) 0x6E, (byte) 0x76,
                                    (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x3B, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x04, (byte) 0x43, (byte) 0x6F, (byte) 0x64, (byte) 0x65,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x0F, (byte) 0x4C, (byte) 0x69, (byte) 0x6E,
                                    (byte) 0x65, (byte) 0x4E, (byte) 0x75, (byte) 0x6D, (byte) 0x62, (byte) 0x65,
                                    (byte) 0x72, (byte) 0x54, (byte) 0x61, (byte) 0x62, (byte) 0x6C, (byte) 0x65,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x07, (byte) 0x76, (byte) 0x61, (byte) 0x6C,
                                    (byte) 0x75, (byte) 0x65, (byte) 0x4F, (byte) 0x66, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x2E, (byte) 0x28, (byte) 0x4C, (byte) 0x6A, (byte) 0x61, (byte) 0x76,
                                    (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67,
                                    (byte) 0x2F, (byte) 0x53, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x6E,
                                    (byte) 0x67, (byte) 0x3B, (byte) 0x29, (byte) 0x4C, (byte) 0x6E, (byte) 0x65,
                                    (byte) 0x74, (byte) 0x2F, (byte) 0x66, (byte) 0x61, (byte) 0x62, (byte) 0x72,
                                    (byte) 0x69, (byte) 0x63, (byte) 0x6D, (byte) 0x63, (byte) 0x2F, (byte) 0x61,
                                    (byte) 0x70, (byte) 0x69, (byte) 0x2F, (byte) 0x45, (byte) 0x6E, (byte) 0x76,
                                    (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x3B, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x12, (byte) 0x4C, (byte) 0x6F, (byte) 0x63, (byte) 0x61,
                                    (byte) 0x6C, (byte) 0x56, (byte) 0x61, (byte) 0x72, (byte) 0x69, (byte) 0x61,
                                    (byte) 0x62, (byte) 0x6C, (byte) 0x65, (byte) 0x54, (byte) 0x61, (byte) 0x62,
                                    (byte) 0x6C, (byte) 0x65, (byte) 0x01, (byte) 0x00, (byte) 0x04, (byte) 0x6E,
                                    (byte) 0x61, (byte) 0x6D, (byte) 0x65, (byte) 0x01, (byte) 0x00, (byte) 0x12,
                                    (byte) 0x4C, (byte) 0x6A, (byte) 0x61, (byte) 0x76, (byte) 0x61, (byte) 0x2F,
                                    (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67, (byte) 0x2F, (byte) 0x53,
                                    (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x6E, (byte) 0x67, (byte) 0x3B,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x06, (byte) 0x3C, (byte) 0x69, (byte) 0x6E,
                                    (byte) 0x69, (byte) 0x74, (byte) 0x3E, (byte) 0x01, (byte) 0x00, (byte) 0x16,
                                    (byte) 0x28, (byte) 0x4C, (byte) 0x6A, (byte) 0x61, (byte) 0x76, (byte) 0x61,
                                    (byte) 0x2F, (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67, (byte) 0x2F,
                                    (byte) 0x53, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x6E, (byte) 0x67,
                                    (byte) 0x3B, (byte) 0x49, (byte) 0x29, (byte) 0x56, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x04, (byte) 0x74, (byte) 0x68, (byte) 0x69, (byte) 0x73, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x09, (byte) 0x53, (byte) 0x69, (byte) 0x67, (byte) 0x6E,
                                    (byte) 0x61, (byte) 0x74, (byte) 0x75, (byte) 0x72, (byte) 0x65, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x03, (byte) 0x28, (byte) 0x29, (byte) 0x56, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x08, (byte) 0x3C, (byte) 0x63, (byte) 0x6C, (byte) 0x69,
                                    (byte) 0x6E, (byte) 0x69, (byte) 0x74, (byte) 0x3E, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x2C, (byte) 0x4C, (byte) 0x6A, (byte) 0x61, (byte) 0x76, (byte) 0x61,
                                    (byte) 0x2F, (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67, (byte) 0x2F,
                                    (byte) 0x45, (byte) 0x6E, (byte) 0x75, (byte) 0x6D, (byte) 0x3C, (byte) 0x4C,
                                    (byte) 0x6E, (byte) 0x65, (byte) 0x74, (byte) 0x2F, (byte) 0x66, (byte) 0x61,
                                    (byte) 0x62, (byte) 0x72, (byte) 0x69, (byte) 0x63, (byte) 0x6D, (byte) 0x63,
                                    (byte) 0x2F, (byte) 0x61, (byte) 0x70, (byte) 0x69, (byte) 0x2F, (byte) 0x45,
                                    (byte) 0x6E, (byte) 0x76, (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65,
                                    (byte) 0x3B, (byte) 0x3E, (byte) 0x3B, (byte) 0x01, (byte) 0x00, (byte) 0x0A,
                                    (byte) 0x53, (byte) 0x6F, (byte) 0x75, (byte) 0x72, (byte) 0x63, (byte) 0x65,
                                    (byte) 0x46, (byte) 0x69, (byte) 0x6C, (byte) 0x65, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x0C, (byte) 0x45, (byte) 0x6E, (byte) 0x76, (byte) 0x54, (byte) 0x79,
                                    (byte) 0x70, (byte) 0x65, (byte) 0x2E, (byte) 0x6A, (byte) 0x61, (byte) 0x76,
                                    (byte) 0x61, (byte) 0x0C, (byte) 0x00, (byte) 0x13, (byte) 0x00, (byte) 0x14,
                                    (byte) 0x07, (byte) 0x00, (byte) 0x14, (byte) 0x0C, (byte) 0x00, (byte) 0x31,
                                    (byte) 0x00, (byte) 0x32, (byte) 0x01, (byte) 0x00, (byte) 0x18, (byte) 0x6E,
                                    (byte) 0x65, (byte) 0x74, (byte) 0x2F, (byte) 0x66, (byte) 0x61, (byte) 0x62,
                                    (byte) 0x72, (byte) 0x69, (byte) 0x63, (byte) 0x6D, (byte) 0x63, (byte) 0x2F,
                                    (byte) 0x61, (byte) 0x70, (byte) 0x69, (byte) 0x2F, (byte) 0x45, (byte) 0x6E,
                                    (byte) 0x76, (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x0C,
                                    (byte) 0x00, (byte) 0x19, (byte) 0x00, (byte) 0x33, (byte) 0x0C, (byte) 0x00,
                                    (byte) 0x1E, (byte) 0x00, (byte) 0x1F, (byte) 0x0C, (byte) 0x00, (byte) 0x0F,
                                    (byte) 0x00, (byte) 0x10, (byte) 0x0C, (byte) 0x00, (byte) 0x11, (byte) 0x00,
                                    (byte) 0x10, (byte) 0x0C, (byte) 0x00, (byte) 0x12, (byte) 0x00, (byte) 0x10,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x0E, (byte) 0x6A, (byte) 0x61, (byte) 0x76,
                                    (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67,
                                    (byte) 0x2F, (byte) 0x45, (byte) 0x6E, (byte) 0x75, (byte) 0x6D, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x05, (byte) 0x63, (byte) 0x6C, (byte) 0x6F, (byte) 0x6E,
                                    (byte) 0x65, (byte) 0x01, (byte) 0x00, (byte) 0x14, (byte) 0x28, (byte) 0x29,
                                    (byte) 0x4C, (byte) 0x6A, (byte) 0x61, (byte) 0x76, (byte) 0x61, (byte) 0x2F,
                                    (byte) 0x6C, (byte) 0x61, (byte) 0x6E, (byte) 0x67, (byte) 0x2F, (byte) 0x4F,
                                    (byte) 0x62, (byte) 0x6A, (byte) 0x65, (byte) 0x63, (byte) 0x74, (byte) 0x3B,
                                    (byte) 0x01, (byte) 0x00, (byte) 0x35, (byte) 0x28, (byte) 0x4C, (byte) 0x6A,
                                    (byte) 0x61, (byte) 0x76, (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61,
                                    (byte) 0x6E, (byte) 0x67, (byte) 0x2F, (byte) 0x43, (byte) 0x6C, (byte) 0x61,
                                    (byte) 0x73, (byte) 0x73, (byte) 0x3B, (byte) 0x4C, (byte) 0x6A, (byte) 0x61,
                                    (byte) 0x76, (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61, (byte) 0x6E,
                                    (byte) 0x67, (byte) 0x2F, (byte) 0x53, (byte) 0x74, (byte) 0x72, (byte) 0x69,
                                    (byte) 0x6E, (byte) 0x67, (byte) 0x3B, (byte) 0x29, (byte) 0x4C, (byte) 0x6A,
                                    (byte) 0x61, (byte) 0x76, (byte) 0x61, (byte) 0x2F, (byte) 0x6C, (byte) 0x61,
                                    (byte) 0x6E, (byte) 0x67, (byte) 0x2F, (byte) 0x45, (byte) 0x6E, (byte) 0x75,
                                    (byte) 0x6D, (byte) 0x3B, (byte) 0x40, (byte) 0x31, (byte) 0x00, (byte) 0x04,
                                    (byte) 0x00, (byte) 0x0E, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04,
                                    (byte) 0x40, (byte) 0x19, (byte) 0x00, (byte) 0x0F, (byte) 0x00, (byte) 0x10,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x19, (byte) 0x00, (byte) 0x11,
                                    (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x19,
                                    (byte) 0x00, (byte) 0x12, (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x10, (byte) 0x1A, (byte) 0x00, (byte) 0x13, (byte) 0x00, (byte) 0x14,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x09,
                                    (byte) 0x00, (byte) 0x15, (byte) 0x00, (byte) 0x16, (byte) 0x00, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x22,
                                    (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x0A, (byte) 0xB2, (byte) 0x00, (byte) 0x01, (byte) 0xB6,
                                    (byte) 0x00, (byte) 0x02, (byte) 0xC0, (byte) 0x00, (byte) 0x03, (byte) 0xB0,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x18,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x09,
                                    (byte) 0x00, (byte) 0x19, (byte) 0x00, (byte) 0x1A, (byte) 0x00, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34,
                                    (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x0A, (byte) 0x12, (byte) 0x04, (byte) 0x2A, (byte) 0xB8,
                                    (byte) 0x00, (byte) 0x05, (byte) 0xC0, (byte) 0x00, (byte) 0x04, (byte) 0xB0,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x18,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x1B,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0C, (byte) 0x00, (byte) 0x01,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0A, (byte) 0x00, (byte) 0x1C,
                                    (byte) 0x00, (byte) 0x1D, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02,
                                    (byte) 0x00, (byte) 0x1E, (byte) 0x00, (byte) 0x1F, (byte) 0x00, (byte) 0x02,
                                    (byte) 0x00, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x31,
                                    (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x07, (byte) 0x2A, (byte) 0x2B, (byte) 0x1C, (byte) 0xB7,
                                    (byte) 0x00, (byte) 0x06, (byte) 0xB1, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x02, (byte) 0x00, (byte) 0x18, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x06, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x21, (byte) 0x00, (byte) 0x1B, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x0C, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x07, (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x10, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x02, (byte) 0x00, (byte) 0x22, (byte) 0x00, (byte) 0x08, (byte) 0x00,
                                    (byte) 0x23, (byte) 0x00, (byte) 0x22, (byte) 0x00, (byte) 0x01, (byte) 0x00,
                                    (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x65, (byte) 0x00,
                                    (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x41, (byte) 0xBB, (byte) 0x00, (byte) 0x04, (byte) 0x59, (byte) 0x12,
                                    (byte) 0x07, (byte) 0x03, (byte) 0xB7, (byte) 0x00, (byte) 0x08, (byte) 0xB3,
                                    (byte) 0x00, (byte) 0x09, (byte) 0xBB, (byte) 0x00, (byte) 0x04, (byte) 0x59,
                                    (byte) 0x12, (byte) 0x0A, (byte) 0x04, (byte) 0xB7, (byte) 0x00, (byte) 0x08,
                                    (byte) 0xB3, (byte) 0x00, (byte) 0x0B, (byte) 0xBB, (byte) 0x00, (byte) 0x04,
                                    (byte) 0x59, (byte) 0x12, (byte) 0x0C, (byte) 0x05, (byte) 0xB7, (byte) 0x00,
                                    (byte) 0x08, (byte) 0xB3, (byte) 0x00, (byte) 0x0D, (byte) 0x06, (byte) 0xBD,
                                    (byte) 0x00, (byte) 0x04, (byte) 0x59, (byte) 0x03, (byte) 0xB2, (byte) 0x00,
                                    (byte) 0x09, (byte) 0x53, (byte) 0x59, (byte) 0x04, (byte) 0xB2, (byte) 0x00,
                                    (byte) 0x0B, (byte) 0x53, (byte) 0x59, (byte) 0x05, (byte) 0xB2, (byte) 0x00,
                                    (byte) 0x0D, (byte) 0x53, (byte) 0xB3, (byte) 0x00, (byte) 0x01, (byte) 0xB1,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x18,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x12, (byte) 0x00, (byte) 0x04,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2A, (byte) 0x00, (byte) 0x0D,
                                    (byte) 0x00, (byte) 0x34, (byte) 0x00, (byte) 0x1A, (byte) 0x00, (byte) 0x35,
                                    (byte) 0x00, (byte) 0x27, (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x02,
                                    (byte) 0x00, (byte) 0x21, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02,
                                    (byte) 0x00, (byte) 0x24, (byte) 0x00, (byte) 0x25, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x26
                            };
                            // Use code from patched-files/src/main/java/net/fabricmc/api/EnvType.java
                        }
                        return classfileBuffer;
                    }
                }
        );
    }
}
