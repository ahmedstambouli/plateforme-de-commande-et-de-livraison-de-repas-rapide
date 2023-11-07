import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';
<<<<<<< HEAD
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
=======
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingPartenaireModule } from './Partenaire/app-routing-partenaire.module'

import { AdminModelModule } from './Admin/admin-model.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { ToastrModule } from 'ngx-toastr';




>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f

@NgModule({
  declarations: [
    AppComponent,
    SingInComponent,
    SingUpComponent,
    
    


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
<<<<<<< HEAD
=======
    AppRoutingPartenaireModule,

    AdminModelModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    

    BrowserAnimationsModule,
    ToastrModule.forRoot(),
>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f

  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
