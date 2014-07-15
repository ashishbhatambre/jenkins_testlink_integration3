cd svnFolder
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username user1 --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
cd Version1
echo this is a new test file no 1 for Source Code component > First.txt
svn update First.txt
svn ci -m "updated"
cd Version2
echo this is 2nd file newly updated > Second.txt
svn update Second.txt
svn ci -m "updated"
cd ..
cd ..
cd ..