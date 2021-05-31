# forest-libbox

	Set of utilities to improve kotlin coding.
	
## Content
	- Try - handle exceptions syntax suggar
	- Extensions for standard collections
	

## Usage

###Gradle

- Add git repository to settings.gradle.kts
```
sourceControl{
    gitRepository(java.net.URI.create("https://github.com/MarcinLesinski/forest-libbox")){
        producesModule("io.stud.forest:libbox")
    }
}
```
- Then add depenndency build.gradle.kts as usual
```
dependencies {
	implementation("io.stud.forest:libbox:0.0.7")
}
```

## License
	This project is licensed under the WTFPL License.

## Author
	Marcin Lesiński
	