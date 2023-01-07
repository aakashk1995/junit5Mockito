# junit5Mockito
Repository for Junit5 and Mockito


Junit5

1. To Add Junit5 dependency we need 3 individual dependencies

To run JUnit 5 tests through Maven, below are the main required dependencies:

•	junit-jupiter-api: It is the main module where all core annotations are located, such as @Test, Lifecycle method annotations and assertions.
•	junit-jupiter-engine: It has test engine implementation which is required at runtime to execute the tests.
•	junit-platform-suite: The @Suite support provided by this module to make the JUnitPlatform runner obsolete.
•	junit-jupiter-params: Support for parameterized tests in JUnit 5.

2. To include All these above dependencies into 1 dependency 
`	<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.1</version>
        <scope>test</scope>
    </dependency>`
3. We should give testMethod name descriptive so that it’s easily understandable what the test method do
4. Annotations Used

   ` @Test
    @BeforeAll, @AfterAll – should be static method they are invoked before class instance is created
    @BeforeEach, @AfterEach –
    @Disabled – to disabled the test
    @DisabledIf – disabled based on condition
    @EnabledIf –enabled based on condition
    @Order @RepeatedTest @Tag @Tags`
	
    If you want to test any method with different input values  then used this annotations

   ` @ParameterizedTest
    @MethodSource("returnDifferentParameters")

    private static Stream<Arguments> returnDifferentParameters(){
        return Stream.of(Arguments.of(4,2),
            Arguments.of(10,5));
    }`

5) 	Assertions Methods Used

	assertEquals(exceptedValue,actualValue)
	assertArrayEquals
	assertLinesMatch
	assertNotEquals
	assertThrowsExactly
	assertThrows
	assertTimeout
	assertInstanceOf


