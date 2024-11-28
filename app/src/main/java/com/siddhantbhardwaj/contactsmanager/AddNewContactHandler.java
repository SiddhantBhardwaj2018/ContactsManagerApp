package com.siddhantbhardwaj.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactHandler
{

    Contacts contacts;
    Context context;
    MyViewModel myViewModel;


    public AddNewContactHandler(Contacts contacts, Context context,MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClick(View view){
        if(contacts.getName() == null || contacts.getEmail() == null){
            Toast.makeText(
                    context,
                    "Fields cannot be empty",
                    Toast.LENGTH_SHORT
            ).show();
        }else{
            Intent intent = new Intent(context,MainActivity.class);
            Contacts c = new Contacts(
                    contacts.getName(),
                    contacts.getEmail()
            );
            myViewModel.addContact(c);
            context.startActivity(intent);
        }
    }

}
