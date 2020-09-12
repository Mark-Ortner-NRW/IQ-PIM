import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InnoqPimSharedModule } from '../../shared/shared.module';

import { LogsComponent } from './logs.component';

import { logsRoute } from './logs.route';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forChild([logsRoute])],
  declarations: [LogsComponent],
})
export class LogsModule {}
