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

package com.perl5.lang.perl.idea.formatter.settings;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.perl5.lang.perl.PerlLanguage;

/**
 * Created by hurricup on 06.09.2015.
 */
public class PerlCodeStyleMainPanel extends TabbedLanguageCodeStylePanel
{
	public PerlCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings)
	{
		super(PerlLanguage.INSTANCE, currentSettings, settings);
	}

	protected void addSpacesTab(CodeStyleSettings settings)
	{
		addTab(new PerlCodeStyleSpacesPanel(settings));
	}

}