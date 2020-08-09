# battleships
To Run:
- Put input file(s) in "input" folder
- Run "gradle run"
- Output file(s) will be in output folder matching input filename
  - Any erroneous inputs will have a .ERROR file containing exception message

To Test:
- Run "gradle test"

Assumptions:
- Erroneous actions (Ships occupying same space, trying to move a ship in a position which doesnt have a ship) should error and not complete simulation