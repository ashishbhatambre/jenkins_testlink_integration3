cd Folder1
set PATH="C:\Program Files (x86)\CollabNet\Subversion Client"
svn checkout --username yadmin --password Collabnet2@ #baseURL#/svn/repos/repo1
cd repo1
cd $Version1$
svn diff > ${First}.patch
svn ci -m "diffences bt versions ${First}.txt"
cd $Version2$
svn diff > ${Second}.patch
svn ci -m "diffences bt versions ${Second}.txt"
cd ..
cd ..
cd ..