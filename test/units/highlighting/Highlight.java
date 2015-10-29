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

package units.highlighting;

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import java.io.File;
import java.io.FileFilter;
import java.text.MessageFormat;

import static java.lang.System.out;

/**
 * Created by pmazzoncini
 */
public class Highlight extends LightPlatformCodeInsightFixtureTestCase
{
	@Override
	protected String getTestDataPath()
	{
		return "test/testdata/highlighting";
	}

	private static final FileFilter NO_DIRS = new FileFilter()
	{
		@Override
		public boolean accept(File pathname)
		{
			return !pathname.isDirectory();
		}
	};

	@Override
	protected boolean isWriteActionRequired()
	{
		return false;
	}

	public void testHighlightWithSomeError()
	{

		for (File file : new File(myFixture.getTestDataPath()).listFiles(NO_DIRS))
		{
			String filename = file.getName();
			out.println(MessageFormat.format("{0} highlited succesfully in {1} milliseconds", filename, myFixture.testHighlighting(false, false, false, filename)));
		}
	}
}
