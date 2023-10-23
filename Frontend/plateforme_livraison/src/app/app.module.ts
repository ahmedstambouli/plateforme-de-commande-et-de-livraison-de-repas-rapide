import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingPartenaireModule } from './Partenaire/app-routing-partenaire.module'






@NgModule({
  declarations: [
    AppComponent,
    SingInComponent,
    SingUpComponent,
    SideBarComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingPartenaireModule,
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }