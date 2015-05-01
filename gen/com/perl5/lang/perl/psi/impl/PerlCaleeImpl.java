// This is a generated file. Not intended for manual editing.
package com.perl5.lang.perl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.perl5.lang.perl.lexer.PerlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.perl5.lang.perl.psi.*;

public class PerlCaleeImpl extends ASTWrapperPsiElement implements PerlCalee {

  public PerlCaleeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PerlVisitor) ((PerlVisitor)visitor).visitCalee(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PerlObjectMethod getObjectMethod() {
    return findChildByClass(PerlObjectMethod.class);
  }

  @Override
  @Nullable
  public PerlObjectMethodObject getObjectMethodObject() {
    return findChildByClass(PerlObjectMethodObject.class);
  }

  @Override
  @Nullable
  public PerlPackageMethod getPackageMethod() {
    return findChildByClass(PerlPackageMethod.class);
  }

  @Override
  @Nullable
  public PerlScalar getScalar() {
    return findChildByClass(PerlScalar.class);
  }

}