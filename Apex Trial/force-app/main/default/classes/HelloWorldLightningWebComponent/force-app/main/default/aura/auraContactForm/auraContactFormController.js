({
    saveContact: function(component, event, helper) {
        var action = component.get("c.saveContactToServer");
        action.setParams({
            firstName: component.get("v.firstName"),
            lastName: component.get("v.lastName"),
            email: component.get("v.email"),
            phone: component.get("v.phone"),
            fax: component.get("v.fax")
        });

        action.setCallback(this, function(response) {
            var state = response.getState();
            if (state === "SUCCESS") {
                var contact = response.getReturnValue();
                component.set("v.savedContact", contact);
                component.set("v.message", "Contact created successfully!");
            } else {
                component.set("v.message", "Error occurred while saving contact.");
                console.error(response.getError());
            }
        });

        $A.enqueueAction(action);
    }
})

