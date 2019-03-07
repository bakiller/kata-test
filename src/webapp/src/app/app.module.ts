import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatCommonModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {GameIdInterceptor} from "./core/interceptors/game-id-interceptor";
import {CoreModule} from "./core/core.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    MatCommonModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: GameIdInterceptor,
    multi: true
  },
    HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
