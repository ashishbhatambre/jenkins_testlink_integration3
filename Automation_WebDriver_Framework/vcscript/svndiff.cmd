cd svnFolder
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username user1 --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
cd Version1
svn diff > First.diff
echo First file updated > First.txt
svn update First.txt
svn diff > First.diff
svn ci -m "diffences bt versions First.txt"
cd Version2
svn diff > Second.diff
echo Second file updated > Second.txt
svn update Second.diff
svn diff > Second.diff
svn ci -m "diffences bt versions Second.txt"
cd ..
cd ..
cd ..