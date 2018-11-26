/**
 * Author: Leah Kuperman and Jillian Baggett
 * Course: CMPS 1600
 * Lab Section: Tuesday at 5pm (2)
 * Assignment: Project 1
 * Date Created: 11/12/18
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
//defining structs for nodes and people

typedef struct Person{
    char email[51];
    char firstname[51];
    char lastname[51];
    char age[51];
    char hometown[51];
    char hobby[51];
}Person_type;

typedef struct List_People_S{
    Person_type* information;
    struct List_People_S* next;
}List_People_T;

typedef struct Connection{
    char email_1[50];
    char email_2[50];
    struct Connection* nextConnect;
}Connection_type;




List_People_T * listHead;
Connection_type * listHead2;
List_People_T * listHead3;
int numStars = 0;
void add(List_People_T* listHead, char email[50]); // to enter the people
void remove1(List_People_T* listHead, char email[50]);
void edit(List_People_T* listHead, char email[50]);
void connect1(Connection_type* listHead2,char* email, char* email2);
Connection_type* makeNodeConnectionP(Connection_type* previous, Connection_type* new, char emailOne[50], char emailTwo[50]);
Person_type* makePerson(Person_type* human, char e[50], char fn[50], char ln[50], char a[3], char ht[50], char ho[50]);
void removeConnections(Connection_type* listHead2, char email[50]);
Connection_type* makeNodeConnectionN(Connection_type* previous, Connection_type* new, char emailOne[50], char emailTwo[50]);
void display(void);
void getFriends(char* email);
void saveNetwork(char* peopleFilename, char* connectionsFilename);
void retrieveNetwork(char* peopleFilename, char* connectionsFilename);


/**
 Makes a person object, to later be housed in a node
 */
Person_type* makePerson(Person_type* human, char e[50], char fn[50], char ln[50], char a[50], char ht[50], char ho[50])
{   //assings each field to the appropriate variable
    strcpy(human->email, e);
    strcpy(human->firstname, fn);
    strcpy(human->lastname, ln);
    strcpy(human->age, a);
    strcpy(human->hometown, ht);
    strcpy(human->hobby, ho);
    return human;
}
/**
 creates a node that houses a person
 */
List_People_T* makeNode(List_People_T* currNode, Person_type* person, List_People_T* nextNode)
{
    
    currNode->information = person;
    printf("%s\n",currNode->information->email);
    currNode->next = nextNode;
    return currNode;
}
/**
 adds a person to the node list
 */
void add(List_People_T* listHead, char email[50])

{
    List_People_T * temp = NULL;
    List_People_T *currNode = NULL;
    int flag = 1; //true, keeps the while loop running
    int flag2 = 0; //keeps track of if email is unique in the while loop
    currNode = listHead;
    //to navigate the list of connections and check each of the emails
    while(flag==1)
    {
        if (currNode != NULL)
        {   //tests for existing email
            printf("Testing if %s == %s\n", currNode->information->email, email);
            if (strncmp(email,currNode->information->email, 50)==0)
            {
                printf("This email already exists.\n");
                break;
            }
            if (currNode->next != NULL)
            {
                currNode = currNode->next;
            }
            
            else //when currNode.next = NULL
            {
                if (flag2 == 0)
                {   List_People_T* newNode = (List_People_T*)malloc(sizeof(List_People_T));
                    Person_type* newPerson = (Person_type*)malloc(sizeof(Person_type));
                    
                    char firstname[50];
                    char lastname[50];
                    char age[3];
                    char hometown[50];
                    char hobby[50];
                    
                    printf("\nEnter first name: ");
                    scanf("%s", &firstname);
                    printf("Enter last name: ");
                    scanf("%s", &lastname);
                    printf("Enter age: ");
                    scanf("%s", &age);
                    printf("Enter hometown: ");
                    scanf("%s", &hometown);
                    printf("Enter hobby: ");
                    scanf("%s", &hobby);
                    
                    
                    //printf("\nEmail: %s\nName: %s %s\nAge: %s\nHometown: %s\nHobby: %s\n", email, firstname, lastname, age, hometown, hobby);
                    newPerson = makePerson(newPerson, email,firstname, lastname, age, hometown, hobby);
                    currNode->next = newNode;
                    newNode->information = newPerson;
                    
                    printf("This is %s %s. He is %s years old. He is from %s and enjoys %s. You can reach him at %s.\n", newPerson->firstname, newPerson->lastname, newPerson->age, newPerson->hometown, newPerson->hobby, newPerson->email);
                    //makeNode(newNode, email, NULL);
                    //currNode->next = newNode;
                    flag = 0;
                }
            }
        }
    }
}

/**
 removes a person (and their connections) from the node list
 */
void remove1(List_People_T* listHead, char email[50])
{
    int flag1 = 1; //true
    List_People_T *prevNode = NULL;
    List_People_T *nextNode = NULL;
    List_People_T *currNode = NULL;
    int flag2 = 0;
    currNode = listHead;
    int count = 0;
    
    while(flag2 == 0)
    {
        if (currNode == NULL)
        {flag2 = 1;}
        else if (strncmp(email,currNode->information->email, 50)==0)
        {
            flag2 = 1; //assures that the email is in the list, and that we should move forward
        }
        else
        {
            count++;
            currNode = currNode->next;
        }
        
    }
    if(flag2 == 0)
    {
        printf("This email is not in the list");
    }
    while(flag1 == 1 && flag2 ==1) //will not execute if email is not in list
    {
        //3 cases for what currNode could be - have only prev (end), have both prev and next(middle). Reason it cant be first node is because we made a fake node essentially to always be head of the list.
        List_People_T* tempNode = NULL;
        tempNode = listHead;
        for (int x=0; x<count-1; x++)
        {
            tempNode = tempNode->next;
        }
        prevNode = tempNode;        //sets prevNode to be the one before the one we want
        
        if (currNode == listHead)
        {//ignore
            currNode = currNode->next;
            printf("At head of list.\n");
        }
        else if((currNode->next == NULL) && strncmp(email,currNode->information->email, 50)==0) //aka end of the list
        {   removeConnections(listHead, email);
            prevNode->next = NULL;
            printf("Removed node.\n");
            flag1 = 0;
        }
        else if (strncmp(email,currNode->information->email, 50)==0)//middle of list
        {   removeConnections(listHead, email);
            nextNode = currNode->next;
            prevNode->next = nextNode;
            printf("Removed node.\n");
            flag1 =0;
        }
        else
        {   prevNode = currNode;
            currNode = currNode->next;
            
        }
    }
}
/**
 Removes a connection (of two emails)
 */
void removeConnections(Connection_type* listHead2, char emailToRemove[50])
{ int flag3 = 1;
    int count = 0;
    Connection_type *prevNode = NULL;
    Connection_type *nextNode = NULL;
    Connection_type *currNode = NULL;
    currNode = listHead2;
    while(flag3 == 1)
    {
        if(currNode == NULL)
        {
            flag3 = 0;
        }
        else if (strncmp(currNode->email_1, emailToRemove, 50) == 0 || strncmp(currNode->email_2, emailToRemove, 50)==0)
        {
            Connection_type* tempNode = NULL;
            tempNode = listHead2;
            for (int x=0; x<count-1; x++)
            {
                tempNode = tempNode->nextConnect;
            }
            prevNode = tempNode;        //sets prevNode to be the one before the one we want
            
            if (currNode == listHead2)
            {//ignore
                currNode = currNode->nextConnect;
                printf("At head of list.\n");
            }
            else if((currNode->nextConnect == NULL) && (strncmp(emailToRemove,currNode->email_1, 50)==0 || strncmp(emailToRemove,currNode->email_1, 50)==0)) //aka end of the list
            {
                prevNode->nextConnect = NULL;
                printf("Removed node.\n");
            }
            else if ((strncmp(emailToRemove,currNode->email_1, 50)==0 || strncmp(emailToRemove,currNode->email_1, 50)==0))  //middle of list
            {
                nextNode = currNode->nextConnect;
                prevNode->nextConnect = nextNode;
                currNode = prevNode;
                printf("Removed node.\n");
            }
            currNode = currNode->nextConnect;
        }
        else
        {
            count++;
            currNode = currNode->nextConnect;
        }
        
    }
}
/**
 Edits a person's name given their email address and the head of the list
 */
void edit(List_People_T* listHead, char email[50])
{
    //finds person in list
    char answer1;
    char answer2;
    char answer3[50];
    List_People_T *currNode = NULL;
    currNode = listHead;
    int flag4 = 1;
    //char curr_e[50] = listHead->information->email[50];
    //printf("Testing if %s == %s\n", currNode->information->email, email);
    while(flag4 == 1)
    {   if (currNode == NULL)
    {
        flag4 = 0;
        break;
    }
        
        printf("Testing if %s == %s\n", currNode->information->email, email);
        if (currNode != NULL && strncmp(currNode->information->email, email, 50) == 0)
        {   //declares and copies in the variables to a temporary variable
            char email_temp[50];
            char firstname[50];
            char lastname[50];
            char age[50];
            char hometown[50];
            char hobby[50];
            strcpy(email_temp, currNode->information->email);
            strcpy(firstname, currNode->information->firstname);
            strcpy(lastname, currNode->information->lastname);
            strcpy(hometown, currNode->information->hometown);
            strcpy(hobby, currNode->information->hobby);
            strcpy(age,currNode->information->age);
            
            printf("Person's email: %s\n", currNode->information->email);
            printf("Person's first name: %s\n", currNode->information->firstname);
            printf("Person's last name: %s\n", currNode->information->lastname);
            printf("Person's hometown: %s\n", currNode->information->hometown);
            printf("Person's hobby: %s\n", currNode->information->hobby);
            printf("Person's name: %s\n", currNode->information->age);
            //begins interaction with user
            printf("Would you like to edit any information? Type Y for yes or N for no\n");
            scanf("%s", &answer1);
            if (answer1 == 'N')
            {
                printf("Goodbye");
                flag4 = 0;
                break;
            }
            else//(answer1 == 'Y')
            { //enters while they want to keep updating information
                while (answer1== 'Y')
                {
                    Person_type *temp = (Person_type*)malloc(sizeof(Person_type));
                    printf("Which field would you like to edit? Type F for first name, L for last name, H for hometown, O for hobby, A for age.\n");
                    scanf("%s", &answer2);
                    if (answer2 == 'F')
                    {
                        printf("Please enter what you would like the new first name to be: \n");
                        scanf("%s", &answer3);
                        printf("%s", answer3);
                        strcpy(firstname, answer3);
                        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
                        currNode->information = temp;
                        printf("New firstname: %s\n", currNode->information->firstname);
                    }
                    else if (answer2 == 'L')
                    {
                        printf("Please enter what you would like the new last name to be: \n");
                        scanf("%s", &answer3);
                        strcpy(lastname, answer3);
                        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
                        currNode->information = temp;
                        printf("New lastname: %s\n", currNode->information->lastname);
                    }
                    else if (answer2 == 'H')
                    {
                        printf("Please enter what you would like the new hometown to be: \n");
                        scanf("%s", &answer3);
                        strcpy(hometown, answer3);
                        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
                        currNode->information = temp;
                        printf("New hometown: %s\n", currNode->information->hometown);
                    }
                    else if (answer2 == 'O')
                    {
                        printf("Please enter what you would like the new hobby to be: \n");
                        scanf("%s", &answer3);
                        strcpy(hobby, answer3);
                        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
                        currNode->information = temp;
                        printf("New hobby: %s\n", currNode->information->hobby);
                    }
                    else// (answer2 = "A")
                    {
                        printf("Please enter what you would like the age to be: \n");
                        scanf("%s", &answer3);
                        strcpy(age, answer3);
                        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
                        currNode->information = temp;
                        printf("New age: %s \n", currNode->information->age);
                    }
                    printf("Would you like to edit any information? Type Y for yes or N for no\n");
                    scanf("%s", &answer1);
                }
            }
            
        }
        
        else
        {
            printf("\nEntered else loop\n");
            currNode = currNode->next;
        }
        
        
    }
    
}
/**
 Connects two email nodes together
 */

void connect1(Connection_type* listHead2,char* email, char* email2)
{ int flag5 = 1;
    int flag6 = 1;
    Connection_type* temp = (Connection_type*)malloc(sizeof(Connection_type));
    Connection_type* currNode = (Connection_type*)malloc(sizeof(Connection_type));
    Connection_type* currNode2 = (Connection_type*)malloc(sizeof(Connection_type));
    currNode= listHead2;
    currNode2 = currNode;
    while(flag6 == 1) //iterates through connections to make sure connection doesn't already exist
    { if ( currNode2 != NULL)
    {   if((strncmp("", currNode2->email_1, 50) == 0 || strncmp("", currNode2->email_2, 50)== 0))
    { flag6 = 0;
        break;
        
    }
        if ((strncmp(email, currNode2->email_1, 50)==0 && strncmp(email2,currNode2->email_2, 50)==0) ||(strncmp(email,currNode2->email_2, 50)==0 && strncmp(email2,currNode2->email_1, 50)==0))
        {
            printf("This connection already exists");
            flag5 = 0;
            flag6 = 0;
            break;
        }
        else
        {
            currNode2 = currNode2->nextConnect;
        }
    }
    else {
        flag6 = 0;
    }
        
    }
    while(flag5==1)
    {   if (currNode == NULL)
    {   printf("error");
        
    }
    else if (strncmp("", currNode->email_1, 50) == 0)
    {
        flag5 = 0;
        break;
    }
        
    else if (currNode->nextConnect == NULL)
    {
        temp = makeNodeConnectionN(currNode, temp, email, email2);
        currNode->nextConnect = temp;
        temp->nextConnect = NULL;
        //printf("%s", currNode->email_1);
        
        //printf("Connected\n");
        flag5 = 0;
    }
    else
    {
        currNode = currNode->nextConnect;
    }
        
    }
}
/**
 Puts emails into "previous"
 */
Connection_type* makeNodeConnectionP(Connection_type* previous, Connection_type* new, char emailOne[50], char emailTwo[50])
{
    strcpy(previous->email_1, emailOne);
    strcpy(previous->email_2, emailTwo);
    previous->nextConnect = new;
    return previous;
}
/**
 Puts emails into "new"
 */
Connection_type* makeNodeConnectionN(Connection_type* previous, Connection_type* new, char emailOne[50], char emailTwo[50])
{
    strcpy(new->email_1, emailOne);
    strcpy(new->email_2, emailTwo);
    previous->nextConnect = new;
    return new;
}
/**
 Displays all of the freinds and connections
 */
void display(void)
{ int q = 1;
    List_People_T* currNode = (List_People_T*)malloc(sizeof(List_People_T));
    currNode = listHead;
    while(q==1)
    { if (currNode == NULL)
    {
        q = 0;
        break;
        
    }
    else
    {
        getFriends(currNode->information->email);
        currNode = currNode->next;
    }
    }
    
}
/**
 Prints the friends of the given person
 */
void getFriends(char* email)
{   int i = 1;
    int j = 1;
    int k = 1;
    char* name[50];
    char* friendName[50];
    List_People_T* temp = (List_People_T*)malloc(sizeof(List_People_T));
    List_People_T* currNode = (List_People_T*)malloc(sizeof(List_People_T));
    List_People_T* currNode3 = (List_People_T*)malloc(sizeof(List_People_T));
    Connection_type* temp2 = (Connection_type*)malloc(sizeof(Connection_type));
    Connection_type* currNode2 = (Connection_type*)malloc(sizeof(Connection_type));
    currNode= listHead;
    currNode2 = listHead2;
    currNode3 = listHead3;
    char arrayOfChars[50]; // a single array of characters
    char friends[50][50];
    int counter = 0;
    
    char nameNode[50];
    for (int i = 0; i <= 50; i++)
    {
        strcpy(friends[i], "");
    }
    while(j == 1)
    {
        if(currNode == NULL)
        {
            j = 0;
            break;
        }
        
        else if (strncmp(currNode->information->email, email, 50) == 0)
        {
            printf("%s:{\n", currNode->information->firstname);
            strcpy(nameNode, currNode->information->firstname);
            break;
        }
        else
        {
            currNode = currNode->next;
        }
        
    }
    while (i == 1)
    {   if (currNode2 == NULL)
    {
        i = 0;
        break;
    }
        if (strncmp(currNode2->email_1, email, 50) == 0)
        {
            strcpy(arrayOfChars,currNode2->email_2);
            strcpy(friends[counter], arrayOfChars);
            counter++;
            currNode2 = currNode2->nextConnect;
        }
        else if (strncmp(currNode2->email_2, email, 50)== 0)
        {
            strcpy(arrayOfChars,currNode2->email_1);
            strcpy(friends[counter], arrayOfChars);
            counter++;
            currNode2 = currNode2->nextConnect;
        }
        else if (currNode2->nextConnect == NULL || strncmp(currNode2->email_1, "", 50) == 0)
        {
            i = 0;
            break;
        }
        else
        {
            currNode2 = currNode2->nextConnect;
        }
    }
    
    currNode = listHead;
    for (int i = 0; i < (counter + 1); i++) //finds their friends
    {  while (k == 1)
    {
        if (currNode == NULL)
        {
            k = 0;
            break;
            
        }
        else if (currNode->information == NULL)
        {
            k = 0;
            break;
        }
        
        else if (strcmp(currNode->information->email, friends[i]) == 0 && strcmp(currNode->information->firstname, nameNode) != 0)
        {
            printf("->%s\n",currNode->information->firstname);
            i++;
            
        }
        
        else
        {currNode = currNode->next;}
    }
        currNode = listHead;
        k=1;
    }
    printf("}\n");
    
}


/*
 Takes the current list of people and the list of connections and saves them into two separate files
 */
void saveNetwork(char* peopleFilename, char* connectionsFilename)
{
    FILE *ppl;
    List_People_T *currPplNode = NULL;
    currPplNode = listHead;
    ppl = fopen(peopleFilename,"w");
    while (currPplNode != NULL)
    {
        fprintf(ppl,"%s | %s | %s | %s | %s | %s\n", currPplNode->information->firstname, currPplNode->information->lastname, currPplNode->information->email, currPplNode->information->age, currPplNode->information->hometown, currPplNode->information->hobby);
        currPplNode = currPplNode->next;
    }
    fclose(ppl);
    
    FILE *cxn;
    Connection_type *currCxnNode = NULL;
    currCxnNode = listHead2;
    cxn = fopen(connectionsFilename,"w");
    while (currCxnNode != NULL)
    {
        fprintf(ppl,"*%s & %s\n", currCxnNode->email_1, currCxnNode->email_2);
        currCxnNode = currCxnNode->nextConnect;
    }
    fclose(cxn);
}

/*
 Takes in two separate files, one for the connections, and one for the people, and loads their information into the two different lists in the program
 */
void retrieveNetwork(char* peopleFilename, char* connectionsFilename)
{
    FILE *ppl;
    int condition = 1;
    List_People_T *currPplNode = NULL;
    currPplNode = listHead;
    char email_temp[50];
    char firstname[50];
    char lastname[50];
    char age[50];
    char hometown[50];
    char hobby[50];
    ppl = fopen(peopleFilename,"r");
    while (!feof(ppl)) //!= EOF)
    {
        Person_type* temp = (Person_type*)malloc(sizeof(Person_type));
        fscanf(ppl,"%s | %s | %s | %s | %s | %s\n", &firstname, &lastname, &email_temp, &age, &hometown, &hobby);
        temp = makePerson(temp, email_temp, firstname, lastname, age, hometown, hobby);
        currPplNode->information = temp;
        currPplNode->next = (List_People_T*)malloc(sizeof(List_People_T));
        currPplNode = currPplNode->next;
        temp = NULL;
        free(temp);
        
        
    }
    fclose(ppl);
    currPplNode = NULL;
    
    FILE *cxn;
    Connection_type *currCxnNode = NULL;
    currCxnNode = listHead2;
    char email1[50];
    char email2[50];
    cxn = fopen(connectionsFilename,"r");
    char c;
    while (!feof(cxn))
    {
        c = fgetc(cxn);
        if (c == '*')
        {
            numStars++;
            fscanf(cxn, "%s & %s\n", &email1, &email2);
            currCxnNode = makeNodeConnectionP(currCxnNode, NULL, email1, email2);
            currCxnNode->nextConnect = (Connection_type*)malloc(sizeof(Connection_type));
            currCxnNode = currCxnNode->nextConnect;
        }
        else
        {
            break;
        }
        
        
    }
    fclose(cxn);
    currCxnNode = NULL;
    
}


int main(int argc, const char * argv[]) {
    listHead = (List_People_T*)malloc(sizeof(List_People_T));
    List_People_T * nextNode = (List_People_T*)malloc(sizeof(List_People_T));
    List_People_T * tail = (List_People_T*)malloc(sizeof(List_People_T));
    
    char listHeadEmail1[50] = "PersonListHead";
    
    listHead2 = (Connection_type*)malloc(sizeof(Connection_type));
    Connection_type * afterHead = (Connection_type*)malloc(sizeof(Connection_type));
    Connection_type * tail2 = (Connection_type*)malloc(sizeof(Connection_type));
    listHead3 = (List_People_T*)malloc(sizeof(List_People_T));
    Person_type* head = (Person_type*)malloc(sizeof(Person_type));
    head = makePerson(head, "head@mail.com", "Head", "Headington", "100", "Heaven", "Leadership");
    Person_type* inTail = (Person_type*)malloc(sizeof(Person_type));
    inTail = makePerson(inTail, "bear.com", "Tail", "Tailington", "100", "tailing", "tail");
    char listHead2Email1[50] = "ConnectionListHead@aol.com";
    char listHead2Email2[50] = "ConnectionListHead@gmail.com";
    
    //testing removeConnections
    
    afterHead = makeNodeConnectionP(afterHead, tail2, "bear.com", "head@mail.com");
    tail2 = makeNodeConnectionP(tail2, NULL, "philrox@aol.com", "bear.com");
    //removeConnections(listHead2, "philrox@aol.com");
    //printf("lH2 email is %s", listHead2->email_1);
    
    //testing making people and changing their attrivutes
    //printf("Email 1 is: %s\n", listHeadEmail1);
    //printf("Email 1 is: %s\n", listHead->information->email);
    Person_type* phil = (Person_type*)malloc(sizeof(Person_type));
    Person_type* jill = (Person_type*)malloc(sizeof(Person_type));
    phil = makePerson(phil, "philrox@aol.com", "Phil", "Phillips", "107", "Philadelphia", "Phishing");
    //printf("This is %s %s. He is %s years old. He is from %s and enjoys %s. You can reach him at %s.\n", phil->firstname, phil->lastname, phil->age, phil->hometown, phil->hobby, phil->email);
    jill = makePerson(jill, "jb123@gmail.com", "Jillian", "Baggett", "19", "Rochester", "Coding");
    listHead2 = makeNodeConnectionP(listHead2, afterHead, listHead2Email1, listHead2Email2);
    //testing edit, add, and remove
    
    listHead->information = head;
    listHead->next = nextNode;
    char cxnFile[500] = "/Users/leahkuperman/Documents/Tulane/Comp Sci Labs/CMPS-1600/Project 1/Social Network 2/Social Network 2/connections.txt";
    char pplFile[500] = "/Users/leahkuperman/Documents/Tulane/Comp Sci Labs/CMPS-1600/Project 1/Social Network 2/Social Network 2/people.txt";
    List_People_T* temp5 = listHead;
    Connection_type* temp6 = listHead2;
    printf("\n");
    while (temp5 != NULL)
    {
        printf("This is %s %s. He is %s years old. He is from %s and enjoys %s. You can reach him at %s.\n", temp5->information->firstname, temp5->information->lastname, temp5->information->age, temp5->information->hometown, temp5->information->hobby, temp5->information->email);
        if (temp5->next->information != NULL)
        {
            temp5 = temp5->next;
        }
        else
        {
            temp5 = NULL;
        }
        
    }
    while (temp6 != NULL)
    {
        printf("Connection: %s, %s\n", temp6->email_1, temp6->email_2);
        temp6 = temp6->nextConnect;
        
    }
    retrieveNetwork(pplFile, cxnFile);
    temp5 = listHead;
    temp6 = listHead2;
    while (temp5 != NULL)
    {
        printf("This is %s %s. He is %s years old. He is from %s and enjoys %s. You can reach him at %s.\n", temp5->information->firstname, temp5->information->lastname, temp5->information->age, temp5->information->hometown, temp5->information->hobby, temp5->information->email);
        if (temp5->next->information != NULL)
        {
            temp5 = temp5->next;
        }
        else
        {
            temp5 = NULL;
        }
        
        
    }
    for (int i=0; i<numStars; i++)
    {
        printf("Connection: %s, %s\n", temp6->email_1, temp6->email_2);
        temp6 = temp6->nextConnect;
    }
    
    
    
    
}



