#!/usr/bin/env python
# coding: utf-8

# In[57]:


import tkinter as tk

root=tk.Tk();
root.title("Login")
root.geometry("300x400")
root.config(bg="grey")
root.resizable(False,False)

Login=tk.Label(root,text="Login",bg="grey",fg="blue",font=("Arial",20,"bold"))
Login.pack(pady=20)

Email=tk.Label(root,text="Email",bg="grey",fg="blue",font=("Arial",15))
Email.place(x=10,y=100)

EmailE=tk.Entry(root)
EmailE.place(x=130,y=105)

Password=tk.Label(root,text="Password",bg="grey",fg="blue",font=("Arial",15))
Password.place(x=10,y=150)

PasswordE=tk.Entry(root,show="*")
PasswordE.place(x=130,y=155)


checkbox = tk.Checkbutton(root, text="I agree to terms",
                          font=("Arial", 12),
                          fg="black", bg="#EAEAEA",
                          onvalue=True, offvalue=False)
checkbox.pack(pady=(110,10))


selected_gender = tk.StringVar(value="None")

radio1 = tk.Radiobutton(root, text="Male", variable=selected_gender, value="Male",
                        font=("Arial", 12), bg="#EAEAEA")
radio2 = tk.Radiobutton(root, text="Female", variable=selected_gender, value="Female",
                        font=("Arial", 12), bg="#EAEAEA")

radio1.pack()
radio2.pack()



selected_fruit = tk.StringVar()
selected_fruit.set("Select a fruit")  # default text

dropdown = tk.OptionMenu(root,selected_fruit, "Apple", "Banana", "Mango", "Orange")
dropdown.config(font=("Arial", 12), bg="lightblue", fg="black", width=15)
dropdown.pack(pady=20)


Submit=tk.Button(root,text="Submit",bg="pink",fg="white",font=("Arial",15))
Submit.pack()


root.mainloop()


# In[ ]:





# In[ ]:




