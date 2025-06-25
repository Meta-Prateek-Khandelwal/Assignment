({
    saveContact : function(component, event, helper) {
        var contact = component.get("v.contact");

        var action = component.get("c.saveContactRecord");
        action.setParams({ contactRec : contact });

        action.setCallback(this, function(response){
            var state = response.getState();
            if (state === "SUCCESS") {
                component.set("v.isSuccess", true);
                component.set("v.isError", false);
                component.set("v.contact", response.getReturnValue());
                component.set("v.message", "Contact saved successfully!");
            } else {
                component.set("v.isSuccess", false);
                component.set("v.isError", true);
                var errors = response.getError();
                component.set("v.message", errors && errors[0] && errors[0].message ? errors[0].message : "Unknown error");
            }
        });
        $A.enqueueAction(action);
    }
})
