import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingPartenaireModule } from './Partenaire/app-routing-partenaire.module'

import { AdminModelModule } from './Admin/admin-model.module';
import { MatDialogModule } from '@angular/material/dialog';



import { ToastrModule } from 'ngx-toastr';





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
    AppRoutingPartenaireModule,
    AdminModelModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),

  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
