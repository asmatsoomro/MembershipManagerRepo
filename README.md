# MembershipManager

# Instructions
1. Open pom.xml with Intelij or Eclipse
2. Run Main.java
3. Open browser on http://localhost:8080

# Insert New Member
POST http://localhost:8080/member
{
        "firstName": "abc",
        "lastName": "xyz",
        "dateOfBirth": 1335205543511,
        "zipCode": 12345
 }

 #Read all members
 GET http://localhost:8080/member

 #Read a specific member
 GET http://localhost:8080/member/{firstName}

#Remove a Member from repository
DELETE http://localhost:8080/member
{
        "firstName": "abc",
        "lastName": "xyz",
        "dateOfBirth": 1335205543511,
        "zipCode": 12345
 }

 #Update an Existing Member
 Put http://localhost:8080/member
 {
         "firstName": "abc",
         "lastName": "www",
         "dateOfBirth": 1335205543511,
         "zipCode": 12345
  }