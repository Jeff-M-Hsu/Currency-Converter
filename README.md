# Currency-Converter

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/87f13555eb034df1a0f6fa0ae0ed490c)](https://www.codacy.com/app/jeff_m_hsu/Currency-Converter?utm_source=github.com&utm_medium=referral&utm_content=Jeff-M-Hsu/Currency-Converter&utm_campaign=badger)

Convert currency based on exchange rates updated from currencylayer API every hour or use exchange rates from any day between today and January 1st of 18 years ago. Their site only mentions up to 16 years ago but for some reason it still returns rates of dates before that.

You can register for a free account at [currencylayer.com](https://currencylayer.com/) for up to 1000 API requests per month. This project was built using Maven.


Usage
----------
Register for a free key at currencylayer and save it as a text file named "key.txt". Then download this jar file and place "key.txt" in the same directory. Open command prompt and cd to where the jar and key are located and enter `java -jar convert.jar` and follow the instructions printed in the console.

To-Do List
----------
 ~~- Implement method to request from the "historical" endpoint of the API~~
 
 ~~- Clean up code wherever possible~~
 
 ~- Create a UI and executable when project functionality is complete~
 - Documentation, testing, refactoring
