cd Folder1
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username yadmin --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
cd $Version1$
svn rm ${First}.txt
svn ci -m "removed"
cd ..
cd ..