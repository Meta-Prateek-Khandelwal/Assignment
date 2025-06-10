trigger TeacherO on Teach__c (before insert, before update) {
    for(Teach__c teacher: Trigger.new){
        if(teacher.subject__c != null && teacher.subject__c.contains('Hindi')){
            teacher.addError('Teachers who teach Hindi cannot be inserted or updated');
        }
    }
}