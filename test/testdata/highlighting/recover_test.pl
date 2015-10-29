# Statements recovery tests

# recover on semicolon
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error>if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got '$'"></EOLError>
$b = 123;   # <= recover till now
$a = 123;   # this one should be parsed ok

# recover on closing brace
{
    $a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got '$'"></EOLError>
    $b = 123
}
$a = 123;   # this one should be parsed ok

# recover on closing regex delimiter
s/someting/$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<error descr="PerlTokenType.LEFT_BRACE expected, got '/'">/</error>ex;
$a = 123;   # this one should be parsed ok

# recover on named block
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'BEGIN'"></EOLError>
BEGIN{
    $a = 123;   # this one should be parsed ok
}

# recover on package keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'package'"></EOLError>
package main;  # this one should be parsed ok

# recover on use keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'use'"></EOLError>
use warnings;  # this one should be parsed ok

# recover on no keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ {something braced is here}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'no'"></EOLError>
no warnings;  # this one should be parsed ok

# recover on sub keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ sub {somethingbad;}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'sub'"></EOLError>
sub abc{$b = 123;};  # this one should be parsed ok

# recover on sub keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ sub {somethingbad;}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'sub'"></EOLError>
sub CORE::abc{$b = 123;};  # this one should be parsed ok

# recover on sub keyword
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ sub {somethingbad;}<error descr="Semicolon expected"> </error> if($somevar)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'sub'"></EOLError>
sub Foo::abc{$b = 123;};  # this one should be parsed ok

# recover on if compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
if(1){$b = 123;};  # this one should be parsed ok

# recover on if compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ  {something braced is here}<error descr="Semicolon expected"> </error>if ($something)<EOLError descr="PerlTokenType.LEFT_BRACE expected, got 'if'"></EOLError>
if(1){$b = 123;};  # this one should be parsed ok

# recover on if compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ  {something braced is here}<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something<EOLError descr="Semicolon expected"></EOLError>
if(1){$b = 123;};  # this one should be parsed ok

# recover on unless compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>unless<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
unless(1){$b = 123;};  # this one should be parsed ok

# recover on given compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>given<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
given($a){$b = 123;};  # this one should be parsed ok

# recover on while compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>while<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
while($a){$b = 123;};  # this one should be parsed ok

# recover on until compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>until<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
until($a){$b = 123;};  # this one should be parsed ok

# recover on for compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '@'"> </error>@ARGV {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
for $a (@ARGV){$b = 123;};  # this one should be parsed ok

# recover on for compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '@'"> </error>@ARGV {something braced is here}
for($a = 1<error descr="PerlTokenType.RIGHT_PAREN expected, got ';'">;</error> $a < 100; $a++<error descr="<Postfix for>, <Postfix foreach>, <Postfix if>, <Postfix unless>, <Postfix until>, <Postfix when>, <Postfix while>, PerlTokenType.%, PerlTokenType.&&, PerlTokenType.*, PerlTokenType.+, PerlTokenType.,, PerlTokenType.-, PerlTokenType.., PerlTokenType./, PerlTokenType.//, PerlTokenType.<, PerlTokenType.<<, PerlTokenType.<=>, PerlTokenType.=, PerlTokenType.=>, PerlTokenType.>, PerlTokenType.>>, PerlTokenType.^, PerlTokenType.cmp, PerlTokenType.eq, PerlTokenType.ge, PerlTokenType.gt, PerlTokenType.le, PerlTokenType.lt, PerlTokenType.ne, PerlTokenType.x, PerlTokenType.| or PerlTokenType.|| expected, got ')'">)</error>{$b = 123;};  # this one should be parsed ok

# recover on foreach compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '@'"> </error>@ARGV {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
foreach $a (@ARGV){$b = 123;};  # this one should be parsed ok

# recover on foreach compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>for @ARGV {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
foreach $a (@ARGV){$b = 123;};  # this one should be parsed ok

# recover on foreach compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '@'"> </error>@ARGV {something braced is here}
foreach($a = 1<error descr="PerlTokenType.RIGHT_PAREN expected, got ';'">;</error> $a < 100; $a++<error descr="<Postfix for>, <Postfix foreach>, <Postfix if>, <Postfix unless>, <Postfix until>, <Postfix when>, <Postfix while>, PerlTokenType.%, PerlTokenType.&&, PerlTokenType.*, PerlTokenType.+, PerlTokenType.,, PerlTokenType.-, PerlTokenType.., PerlTokenType./, PerlTokenType.//, PerlTokenType.<, PerlTokenType.<<, PerlTokenType.<=>, PerlTokenType.=, PerlTokenType.=>, PerlTokenType.>, PerlTokenType.>>, PerlTokenType.^, PerlTokenType.cmp, PerlTokenType.eq, PerlTokenType.ge, PerlTokenType.gt, PerlTokenType.le, PerlTokenType.lt, PerlTokenType.ne, PerlTokenType.x, PerlTokenType.| or PerlTokenType.|| expected, got ')'">)</error>{$b = 123;};  # this one should be parsed ok

# recover on when compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>when<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
when($a){$b = 123;};  # this one should be parsed ok

# recover on when compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>when ($something) {something braced is here}
when($a){$b = 123;};  # this one should be parsed ok

# recover on default compound
$a =<error descr="<expression> expected, got 'ô'"> </error>ôûâàôûâàæ<error descr="Semicolon expected"> </error>if<error descr="PerlTokenType.LEFT_PAREN expected, got '$'"> </error>$something {something braced is here}<EOLError descr="Semicolon expected"></EOLError>
default{$b = 123;};  # this one should be parsed ok

