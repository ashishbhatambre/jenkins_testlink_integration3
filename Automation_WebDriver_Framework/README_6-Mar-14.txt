README to create the test files

The framework components are already developed to handle test case scripts configured unders testcases folder. 
You need to configure your test cases in config.properties testcaseorder parameter before running your test cases.

testcaseorder=testcase1.test,testcase2.test,testcase3.test...testcaseN.test

The property file can be used to configure general settings of your test cases. 


To do
Just create test case files with .test or any extensions.
Configure all test cases in property file to run your test cases.
Save all the Uploaded files into the folder C:\Uploaded_Files\... for the Attachement of the files in CTF.

Lets have a look on simple test case file created to handle google search.

#The below content is saved in file named googlesearch.test
name=Test Collabnet in Google Search
url=search/
wait=30
typeElementByName=q=collabnet
clickElementByName=btnG
waitUntilById=resultStats
searchElementValueByTagName=body=Collabnet

#config.properties file settings to handle this test case
testcaseorder=googlesearch.test
baseURL=http://www.google.com/

Now lets see all scripts for my framework.



url
====
To open the relative url

Usage: 

url=sf/sfmain/do/home


typeElementByName
==================
Use this with the name of the element and the parameter

Usage: 

typeElementByName=username=admin

<input type="username">


clickImage
============
Provide the name of the image

Usage: 

clickImage=sf-images/icons/user_picker_icon.png.pagespeed.ce.mN8oEz1hEH.png

<a href="some JavaScript" ><img src="sf-images/icons/user_picker_icon.png.pagespeed.ce.mN8oEz1hEH.png"></a>


switchToPopupWindow
===================
To switch the control to Popup window

Usage: 

switchToPopupWindow


switchToMainWindow
===================
To switch the control to the main window

Usage: 

switchToMainWindow


clickElementByName
===================
To click on any element using the name attribute

Usage:
clickElementByName=saveButton

<input type="buttton" name="saveButton">


submitElementByName
===================
To submit using the name of the element. 
If this current element is a form, or an element within a form, then this will be submitted to the remote server.

Usage:

submitElementByName=saveButton
submitElementByName=username

<input type="submit" name="save">
<form name="save" action=...>
<input type="text" name="username">


submitElementByCssSelector
===========================
To submit using the name of CSS Selector. 
If this current element is a form, or an element within a form, then this will be submitted to the remote server.

Usage: 
submitElementByCssSelector=cssName


submitElementById
==================
To submit using the id of the element

Usage:
submitElementById=save

<input type="submit" id="save">
<form id="save" action=...>
<input type="text" id="username">


searchElementValueByTagName
===========================
Find the existance of the element by tagname.

Usage:
searchElementValueByTagName=input
searchElementValueByTagId=username

<input type="text" id="username">
<select>
<option value="Collabnet" selected>Collabnet</option>
<option value="Google">Google</option>
</select>


selectOptionValueByIdValue
==========================
Select an option by providing the ID

Usage:
selectOptionValueByIdValue=company

<select id="company">
<option value="Collabnet" selected>Collabnet</option>
<option value="Google">Google</option>
</select>


selectOptionValueByNameValue
============================
Select an option by providing value.

Usage:
selectOptionValueByNameValue=company=Google // Google will be selected

<select id="company">
<option value="Collabnet" selected>Collabnet</option>
<option value="Google">Google</option>
</select>


selectOptionValueByIdIndex
===========================
Select any option from combo box using the index

Usage: 
selectOptionValueByIdIndex = company=0 // Collabnet will be selected
selectOptionValueByIdIndex = company=1 // Google will be selected

<select id="company">
<option value="Collabnet">Collabnet</option>
<option value="Google">Google</option>
</select>


selectButtonByValue
====================
Select radio button using the value.

Usage:
selectButtonByValue=ON
selectButtonByValue=OFF

<input type="radio" value="ON">
<input type="radio" value="OFF">


clickHrefByLinkText
===================
Click link using the text

Usage: 
clickHrefByLinkText=Log In

<a href="some link">Log In</a>


clickHrefByURL
===============
Click link using url

Usage: 

clickHrefByURL=some link
<a href="some link">Log In</a>


typeElementById
===============
To enter a value in the text field using the id of the element

Usage:
typeElementById=username=raji

<input type="text" id="username">


clickElementById
================
Click the element using the ID.

Usage:
clickElementById=save

<input type="submit" id="save">


closeDriver
============
This should be called at the end of all your test cases. The best way to close the driver is at the last test case file last line.

Usage: 

closeDriver


waitUntilById
===============
To wait until the page is loaded with the element whose id is provided. This is an alternate way to calling wait method.

Usage:
waitUntilById = save

<input type="submit" id="save">


wait
====
To wait until the provided time (in milliseconds)

Usage:

wait=1000


waitUntilByName
===============
To wait until the page is loaded with the element whose name is provided. This is an alternate way to calling wait method.

Usage:
waitUntilByName = submit

<input type="submit" name="submit">



CheckBox selection
===================
To select the field values in the Table using checkbox. The command is used to check(select) the box of the required field using the number of list items

Usage:
selectCheckBoxFromList=_listItem=0 


cancelJavaScriptConfirm
========================
To cancel the options of javascript window "CANCEL".

Usage:
cancelJavaScriptConfirm


acceptJavaScriptConfirm
========================
To accept the options of Javascript window "OK".


Usage:
acceptJavaScriptConfirm


typeValueForFileField
=======================
To enter the file name in the field of File Type.

Usage:
typeValueForFileField=F:\Image353.jpg


selectCheckBox
===============
To select the check box

Usage:


selectRadioButton
=================
To select the radio button

Usage:selectRadioButton=XML



runJavaScriptPopToMain
==========================
To handle the JavaScript Window using the Functional Methods of JavaScript.

Usage:
runJavaScriptPopToMain=javascript:submitForm(document.addAttachment, 'submit')



withoutPopCloseSwitchToMainWindow
====================================
To close the pop up window after the process overs and to move to immediate main window.

Usage:
withoutPopCloseSwitchToMainWindow



clickSpanElementByCssSelector
===================================
To click on to the Columns in the Planning Board of Tracker Component. Use the CSS selector of the Column name and value of the column.

Usage:
clickSpanElementByCssSelector=combo-arrow=0

Desc:	Column 0 points to First column in the Board. The number starts from 0-3 which points 4 columns.




clickCssSelectorAndValueForSpan
===================================
After click on to Column in the planning board select the Planning folder in the column using the span value of the drop down menu and add the Planning folder which wants to select

Usage
clickCssSelectorAndValueForSpan=tree-title=Plan_Folder1




dragElementToByCSS
=======================
To Drag the artifact from one location to another locations use the element commands and use the Title of the artifact which have to be move from one column to amother.

eg:
dragElementToByCSS=item ui-corner-all ui-draggable=artifact6=scroll-area=artifact5

Desc: Artifact title from source to Artifact tile of destination.




MULTI ATTACHMENT OF FILES:

typeFileFieldValueForName
===================================
To attach more than one file in Attachments, click on the "Attach another file" options for the number of files which is need to attach. Then use this command and specify the file number along with the method to define the multiple attachments.


Usage: 

typeFileFieldValueForName=file[1]=[URL of the file to be attached]
typeFileFieldValueForName=file[2]=[URL of the file to be attached]
....

Sample Code for this multiple attachment:

//Attaching files for Artifact
clickElementById=createArtifact
wait=300
clickHrefByLinkText=Attach another file
wait=300
clickHrefByLinkText=Attach another file
wait=300
typeFileFieldValueForName=file[1]=F:\Automation-WebDriver\SEL-AutomationFramework\testcases\export.test
wait=300
typeFileFieldValueForName=file[2]=F:\Automation-WebDriver\SEL-AutomationFramework\testcases\test1.test
wait=300
typeFileFieldValueForName=file[3]=F:\Automation-WebDriver\SEL-AutomationFramework\testcases\test2.test




====================================================
clickSpanElementByCssSelector=artifact-expander
===================================================
To seperate the collapsed artifact[Parent- child relationship] in the list view of artifacts which displays in Board View are used with this method.

Usage:

clickSpanElementByCssSelector=artifact-expander 



==================================================
clickElementByCssSelector=DropDownRight=1
=================================================


Usage:






=====================================
clickImage=imagename.gif
=====================================

The plus image in Category and Trackers Sub directory and sub folders use the following.


clickImage=imagename.gif (in case if there is one image). 


If you have more images of the same name, you can invoke them by adding the index value as follows.

usage:

	clickImage=imagename.gif=0
	clickImage=imagename.gif=1

===================================================================
clickElementByCssSelector=i=icon-refresh=0 [Refreshing the Boards] 
===================================================================
To refresh the Swimelanes in Boards with 3 seperate columns use the following method.


usage:

clickElementByCssSelector=i=icon-refresh=0

==========================================================
clickElementByCssSelector=item-key-icon trackerIcon
==========================================================
To Edit the artifact cards in the swimelanes use the Icon Css selector name in the following methods.

usage:

clickSpanElementByCssSelector=icon-edit

===================================================================================
dragElementToByCSS=item ui-corner-all ui-draggable=artf1001=scroll-area=artifact1
====================================================================================
To move an Artifact card to the another planning folder which does'nt have any artifact cards in it then go with the method which is below


usage:


dragElementToByCSS=item ui-corner-all ui-draggable=artf1001=scroll-area=artifact1


USER MANAGEMENT API:

===============================================================
custom=attribute=attributeValue=type=text
===============================================================
To type the text inside the text box, give the attribute name followed by custom and attributeValue, and followed by type of the attribute and the text need to be present in the textbox.


Usage:

	custom=qa-name=username=type=User1

To type the user name in the UserName textbox.


===============================================================
custom=attribute=attributeValue=checkboxclick=index
===============================================================
To select the checkbox, provide the attribute name > attribute value > type the command as "checkboxclick" and provide the index value of the text box

Usage:

	custom=qa-name=restrictUser=checkboxclick=1
	
To select the type of the user in user creation page.


==============================================================
custom=attribute=attributeValue=submit
==============================================================
To click on submit button, provide attribute name > value of the attribute and button name

Usage:



==============================================================
custom=attribute=attributeValue=radioclick
==============================================================

Usage:





------------------
BODY TAG:
------------------
To verify the Breadcrums around the CTF use the object "#NEXT#" inside the breadcrumbs which follows with the content next to it using the Method "searchElementValueByTagName".

usage:

1. searchElementValueByTagName=Body=NewProject#NEXT#Documents#NEXT#&Document_Folder&#NEXT#List Documents


2. Also use this object in verification message where the message contains with highlighted text.
eg: User Group #NEXT#test#NEXT# has been created successfully.




General guidline for handling popup window.

When you open popup winodow in your test case you must use switchToPopupWindow just after opening your popup window. 
After completing all tests you must call switchToMainWindow to move your webdriver to your main window for testing.

Ex:

clickHrefByLinkText = Edit User
wait = 400 //or you may call waitUntilByName, waitUntilById scripts
switchToPopupWindow
typeElementByName=firstName=Rajeshwari
typeElementByName=company=Collabnet
submitElementById=Save
clickHrefByLinkText=Close // all done, call switchToMainWindow to to your main page
switchToMainWindow