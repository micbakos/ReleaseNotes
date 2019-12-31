# Release Notes

This repository contains a simple script that automates the compilation of [Android Beat's](https://github.com/taxibeat/) release notes documenation.

It uses [kscript](https://github.com/holgerbrandl/kscript) to read an execute the commands written in Kotlin.

## Reasoning
The organization abides by the scrum agile methodology and the mobile team follows the git flow branching model. Before every release the team copiles 
all the work that has been done on each sprint. The document is a list of Github's Pull Requests that link to a Jira issue ticket.

This goal of this script is to laverage the infrastracture of the company and the mobile team to automate this task. The release notes are compiled into a Markdown file.

## Installation
* Clone the project
* Install kscript. Follow the steps described [here](https://github.com/holgerbrandl/kscript#installation).

## Run the script
The script uses the following arguments in this order
* directory: This is the git directory of the project
* project: The mobile team releases two applications named `passenger` and `driver`. 
These names filter out the pull requests that are not relevant to the released application.
* from: The commit id from which the script will start collecting the notes. This can be a hash or a tag
* to: The commit id on where to stop collecting notes.

### Example

**Command**:
```
./notes.kts /path/to/project passenger RELEASE-01 RELEASE-02
```

**Output in Markdown**:  
[ID-1000](https://link.to.issue/id/id-1000) Awesome work on the passenger project [#850](https://github.com/organization/project/pull/850)  
[ID-1001](https://link.to.issue/id/id-1001) Bug fix on passenger [#851](https://github.com/organization/project/pull/851)  
