import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InnoqPimSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ToastModule } from 'primeng/toast';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forChild([HOME_ROUTE]), ToastModule],
  declarations: [HomeComponent],
})
export class InnoqPimHomeModule {}
