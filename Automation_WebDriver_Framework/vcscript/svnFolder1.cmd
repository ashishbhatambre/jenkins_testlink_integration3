mkdir Folder1
cd Folder1
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username yadmin --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
svn mkdir $Version1$
cd $Version1$
echo this is a test file for source code component > ${First}.txt
svn add ${First}.txt
svn ci -m "1st file"
svn mkdir $Version2$
cd $Version2$
echo this is a test file2 for Source code component > &Second&.txt
svn add &Second&.txt
svn ci -m "2nd File"
svn mkdir &Version3&
cd &Version3&
echo This is 3rd file > &{Third}.txt
svn add &{Third.txt}
svn ci -m "3rd File"
cd ..
cd ..
cd ..