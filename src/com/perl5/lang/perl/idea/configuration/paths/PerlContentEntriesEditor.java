/*
 * Copyright 2015 Alexandr Evstigneev
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

package com.perl5.lang.perl.idea.configuration.paths;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ui.configuration.CommonContentEntriesEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import com.intellij.openapi.vfs.VirtualFile;
import com.perl5.lang.perl.idea.modules.JpsPerlLibrarySourceRootType;
import com.perl5.lang.perl.idea.project.PerlMicroIdeSettingsLoader;
import com.perl5.lang.perl.idea.settings.Perl5Settings;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.module.JpsModuleSourceRootType;

import java.util.List;

/**
 * Created by hurricup on 07.06.2015.
 */
public class PerlContentEntriesEditor extends CommonContentEntriesEditor
{
	public PerlContentEntriesEditor(String moduleName, ModuleConfigurationState state)
	{
		super(moduleName, state, JavaSourceRootType.SOURCE, JavaSourceRootType.TEST_SOURCE, JpsPerlLibrarySourceRootType.INSTANCE);
	}

	public PerlContentEntriesEditor(String moduleName, ModuleConfigurationState state, JpsModuleSourceRootType<?>... rootTypes)
	{
		super(moduleName, state, rootTypes);
	}

	@Override
	public void apply() throws ConfigurationException
	{
		Perl5Settings mySettings = Perl5Settings.getInstance(getModel().getProject());

		List<String> libRoots = mySettings.libRootUrls;
		libRoots.clear();

		for (VirtualFile entry : getModel().getSourceRoots(JpsPerlLibrarySourceRootType.INSTANCE))
			libRoots.add(entry.getUrl());

		PerlMicroIdeSettingsLoader.applyClassPaths(getModel());
	}
}
