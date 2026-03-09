#Sirma Academy Final Project- а simple Spring Boot application designed to identifies the pair of football players who have played together in common matches for the longest time and the duration for each of those matches

 How it works:
1.  On startup, the app automatically reads data from four CSV files (teams,players, matches, and records) located in the resources folder.
2. It can recognize dates in many different formats, no matter how they are written in the CSV.
3. It calculates the exact minutes two players shared in each match. If a player doesn't have an end time (NULL), the app assumes they played until the 90th minute.
4. You can see the final result (the top pair or pairs and their match history) by visiting the local URL in your browser.

The application expects the following files in `src/main/resources/`:
* teams.csv: ID	Name	ManagerFullName	Group
* players.csv: ID	TeamNumber	Position	FullName	TeamID
* matches.csv: ID	ATeamID	BTeamID	Date	Score
* records.csv: ID	PlayerID	MatchID	fromMinutes	toMinutes


- Java 17 & Spring Boot
- H2 Database (In-memory)
- Maven
