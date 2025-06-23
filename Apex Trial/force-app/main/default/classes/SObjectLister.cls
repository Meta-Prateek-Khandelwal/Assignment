public class SObjectLister {
    public static List<String> getAllSObjects() {
        Map<String, Schema.SObjectType> globalDescribe = Schema.getGlobalDescribe();
        List<String> sObjectNames = new List<String>();

        for(String sObjectName : globalDescribe.keySet()){
            sObjectNames.add(sObjectName);
        }

        sObjectNames.sort();
        return sObjectNames;
    }
}