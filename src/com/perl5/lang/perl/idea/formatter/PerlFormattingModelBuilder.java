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

package com.perl5.lang.perl.idea.formatter;

import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.perl5.lang.perl.PerlLanguage;
import com.perl5.lang.perl.idea.formatter.settings.PerlCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by hurricup on 03.09.2015.
 */
public class PerlFormattingModelBuilder implements FormattingModelBuilder, PerlFormattingTokenSets
{

	private static SpacingBuilder createSpacingBuilder(@NotNull CommonCodeStyleSettings settings, @NotNull PerlCodeStyleSettings perlSettings)
	{
		return new SpacingBuilder(settings.getRootSettings(), PerlLanguage.INSTANCE)
				// standard settings
				.around(OPERATORS_ASSIGNMENT).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
				.around(OPERATORS_EQUALITY).spaceIf(settings.SPACE_AROUND_EQUALITY_OPERATORS)
				.around(OPERATORS_RELATIONAL).spaceIf(settings.SPACE_AROUND_RELATIONAL_OPERATORS)
				.around(OPERATORS_LOGICAL).spaceIf(settings.SPACE_AROUND_LOGICAL_OPERATORS)
				.around(OPERATORS_BITWISE).spaceIf(settings.SPACE_AROUND_BITWISE_OPERATORS)
				.around(OPERATORS_ADDITIVE).spaceIf(settings.SPACE_AROUND_ADDITIVE_OPERATORS)
				.around(OPERATORS_MULTIPLICATIVE).spaceIf(settings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
				.around(OPERATORS_SHIFT).spaceIf(settings.SPACE_AROUND_SHIFT_OPERATORS)
				.around(OPERATORS_UNARY).spaceIf(settings.SPACE_AROUND_UNARY_OPERATOR)

				.before(OPERATOR_COMMA).spaceIf(settings.SPACE_BEFORE_COMMA)
				.after(OPERATOR_COMMA).spaceIf(settings.SPACE_AFTER_COMMA)

				.afterInside(COLON, TRENAR_EXPR).spaceIf(settings.SPACE_AFTER_COLON)

				.afterInside(SEMICOLON, FOR_ITERATOR).spaceIf(settings.SPACE_AFTER_SEMICOLON)
				.beforeInside(SEMICOLON, FOR_ITERATOR).spaceIf(settings.SPACE_BEFORE_SEMICOLON)

				.between(RESERVED_TERMS_BLOCKS, BLOCK).spaceIf(settings.SPACE_BEFORE_DO_LBRACE)
				.beforeInside(STATEMENT_MODIFIERS, STATEMENT).spaceIf(settings.SPACE_BEFORE_WHILE_KEYWORD)

				.between(RESERVED_COMPOUND_CONDITIONAL, CONDITIONAL_BLOCK).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.between(RESERVED_FOR, FOR_ITERATOR).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)

				.afterInside(RESERVED_IF, IF_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_UNLESS, UNLESS_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_WHILE, WHILE_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_UNTIL, UNTIL_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_FOR, FOR_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_FOREACH, FOREACH_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)
				.afterInside(RESERVED_WHEN, WHEN_STATEMENT_MODIFIER).spaceIf(settings.SPACE_BEFORE_IF_PARENTHESES)

				.beforeInside(BLOCK, BLOCK_CONTAINERS).spaceIf(settings.SPACE_BEFORE_IF_LBRACE)

				.afterInside(LEFT_PAREN, CONDITION_LIKE_ELEMENTS).spaceIf(settings.SPACE_WITHIN_IF_PARENTHESES)
				.beforeInside(RIGHT_PAREN, CONDITION_LIKE_ELEMENTS).spaceIf(settings.SPACE_WITHIN_IF_PARENTHESES)

				.before(CONTINUE_BLOCK).spaceIf(settings.SPACE_BEFORE_ELSE_KEYWORD)
				.before(RESERVED_ELSE).spaceIf(settings.SPACE_BEFORE_ELSE_KEYWORD)
				.before(RESERVED_ELSIF).spaceIf(settings.SPACE_BEFORE_ELSE_KEYWORD)
				.before(RESERVED_DEFAULT).spaceIf(settings.SPACE_BEFORE_ELSE_KEYWORD)

						// unconditional
				.beforeInside(SEMICOLON, STATEMENT).spaces(0)
				.before(HEREDOC_END).none()
				.around(OPERATORS_STR).spaces(1)

						// perl specific
				.after(RESERVED_VARIABLE_DECLARATION).spaceIf(perlSettings.SPACE_AFTER_VARIABLE_DECLARATION_KEYWORD)
				.around(OPERATOR_COMMA_ARROW).spacing(1, Integer.MAX_VALUE, 0, true, 1)
				;
	}

	@NotNull
	@Override
	public FormattingModel createModel(PsiElement element, CodeStyleSettings settings)
	{
		CommonCodeStyleSettings commonSettings = settings.getCommonSettings(PerlLanguage.INSTANCE);
		PerlCodeStyleSettings perlSettings = settings.getCustomSettings(PerlCodeStyleSettings.class);
		SpacingBuilder spacingBuilder = createSpacingBuilder(commonSettings, perlSettings);
		PerlFormattingBlock block = new PerlFormattingBlock(element.getNode(), null, null, commonSettings, perlSettings, spacingBuilder);
		return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset)
	{
		return null;
	}
}
