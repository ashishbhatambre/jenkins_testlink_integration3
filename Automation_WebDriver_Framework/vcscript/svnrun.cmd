cd svnFolder
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username user1 --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
svn mkdir subdir
cd subdir
echo this is a test file > l.txt
echo this is a test file > o.txt
svn add l.txt o.txt
svn ci -m "add"
cd ..
cd ..