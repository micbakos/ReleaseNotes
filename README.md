![Quality CI](https://github.com/micbakos/ReleaseNotes/workflows/Quality%20CI/badge.svg)

# Release Notes

This repository contains a simple script that compiles a list of pull request merge commits and uses them to display a readbale list of release notes.

## Reasoning
This script is currently used by the [Beat](https://github.com/beatlabs) mobile team. The organization abides by the scrum agile methodology and the mobile team follows the git flow branching model. Before every release the team compiles 
all the work that has been done on each sprint. The final document is a list of Github's Pull Requests that link to a Jira issue ticket.

This goal of this script is to laverage the infrastracture of the company and the mobile team's workflow to automate this task. The release notes are compiled into a Markdown file.

## Dynamic configuration
In order for the script to run dynamically, the user needs to create a configuration file (in Json or Yaml format) and place it anywhere inside their project. **The file needs to be named `notesConfig.[json|yaml|yml]`**

The configuration file needs to define the following attributes:
- `variants`: Each variant is a representation of a project that outputs release notes. Some repositories may consist of more than one project.
  - `name`: This is the name of the project that is also given to the script input in order to identify which notes to output.
  - `categories`: Each category is a set of pull requests that can be grouped together.
    - `title`: The title to output before the list of pull requests of each category.
    - `regex`: The regex representation of the branch that belongs to the given category.
- `links`: The title of the given pull request may contain links to issues on an issue tracker. This object describes the information needed to replace the issue ids with links to the actuall issue.
  - `regex`: The regex representation of an issue.
  - `url`: The replacement url of each issue in order to transform it to a link.

### Example of a configuration file:

#### Yaml
```yaml

variants:
- name: passenger
  categories:
  - title: Product
    regex: taxibeat\/(passenger|all)\/(feature)\/(?!infra).+\/.+
  - title: Infra
    regex: taxibeat\/(passenger|all)\/(feature|chapter)\/infra\/.+
  - title: Bugs
    regex: taxibeat\/(passenger|all)\/(bugfix|hotfix)\/.+
- name: driver
  categories:
  - title: Product
    regex: taxibeat\/(driver|all)\/(feature)\/(?!infra).+\/.+
  - title: Infra
    regex: taxibeat\/(driver|all)\/(feature|chapter)\/infra\/.+
  - title: Bugs
    regex: taxibeat\/(driver|all)\/(bugfix|hotfix)\/.+
links:
- url: https://jira.taxibeat.com/browse/$1
  regex: "\\[(\\D+-\\d+)\\]"

```

#### JSON
```json
{
  "variants": [
    {
      "name": "passenger",
      "categories": [
        {
          "title": "Product",
          "regex": "taxibeat\\/(passenger|all)\\/(feature)\\/(?!infra).+\\/.+"
        },
        {
          "title": "Infra",
          "regex": "taxibeat\\/(passenger|all)\\/(feature|chapter)\\/infra\\/.+"
        },
        {
          "title": "Bugs",
          "regex": "taxibeat\\/(passenger|all)\\/(bugfix|hotfix)\\/.+"
        }
      ]
    },
    {
      "name": "driver",
      "categories": [
        {
          "title": "Product",
          "regex": "taxibeat\\/(driver|all)\\/(feature)\\/(?!infra).+\\/.+"
        },
        {
          "title": "Infra",
          "regex": "taxibeat\\/(driver|all)\\/(feature|chapter)\\/infra\\/.+"
        },
        {
          "title": "Bugs",
          "regex": "taxibeat\\/(driver|all)\\/(bugfix|hotfix)\\/.+"
        }
      ]
    }
  ],
  "links": [
    {
      "url": "https://jira.taxibeat.com/browse/$1",
      "regex": "\\[(\\D+-\\d+)\\]"
    }
  ]
}

```
This project contains two variants: **passenger** and **driver**. These names can be used when running the script to output the release notes of each project.
Each variant contains categories such as `Product`, `Infra` and `Bugs`. These categories are distinguised themselves by the structure of the branch from which they are merged.
Finally the every issue id occurance can be replaced with the issue's link.

## Output format
The output is printed in Markdown style with the following format
```
**Category 1**
<Title of pull request> #<pull request id>
<Title of pull request> #<pull request id>
  ...
**Category 2** 
<Title of pull request> #<pull request id>
  ...
```

## Installation and running

#### Manual installation
* Clone the project
* Run notes script with the following command inside the `ReleaseNotes/` directory
```
./notes /path/to/project variant FROM-RELEASE-TAG TO-RELEASE-TAG /path/to/output/file
```

#### Installation with Homebrew
* Run the following command
```bash
brew install micbakos/tap/release-notes
```
* And then run with the following command
```bash
notes /path/to/project variant FROM-RELEASE-TAG TO-RELEASE-TAG /path/to/output/file
```

#### Arguments: 
* The path of the project's location
* The chosen variant as defined in the configuration file
* The commit from which the script will search the merge commits
* The commit to which the script will stop the search
* [OPTIONAL] The output file path. If no file specified the script will report to std-out.

Both of the from-to attributes can be represented by the commit's hash or tag. 
