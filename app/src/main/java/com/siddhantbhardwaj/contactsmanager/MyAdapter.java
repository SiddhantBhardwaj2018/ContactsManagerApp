package com.siddhantbhardwaj.contactsmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhantbhardwaj.contactsmanager.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactsViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public ArrayList<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,
                        parent,
                        false
                );
        return new ContactsViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contacts currentContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        if(contacts != null){
            return contacts.size();
        }else{
            return 0;
        }
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;

        public ContactsViewHolder(ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }

}
