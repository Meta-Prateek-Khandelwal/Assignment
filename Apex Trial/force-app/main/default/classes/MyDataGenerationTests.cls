@IsTest
private class MyDataGenerationTests {
  @TestSetup
  static void loadTestDataFromStaticResource() {
    // GIVEN
    List<sObject> accounts = Test.loadData(Account.SObjectType, 'otherAccountData');
  }
  @IsTest
  static void testmyDataGenerationTests1() {
    // WHEN
    List<Account> acclist = [SELECT ID FROM Account];
    // THEN
    System.assert(acclist.size() == 15, 'Expected 15 accounts');
  }
}