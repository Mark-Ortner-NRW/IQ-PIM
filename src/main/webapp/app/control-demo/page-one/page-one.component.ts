import { Component, OnInit } from '@angular/core';
import { ColorPickerModule } from 'primeng/colorpicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-page-one',
  templateUrl: './page-one.component.html',
  styleUrls: ['page-one.component.scss'],
})
export class PageOneComponent implements OnInit {
  message: string;
  color: any = '#24244c';
  brands?: IBrand[] | any;
  cols: any[] = [
    { field: 'id', header: 'ID' },
    { field: 'name', header: 'Name' },
    { field: 'description ', header: 'Description' },
    { field: 'createdAt', header: 'Created At' },
  ];

  constructor(protected brandService: BrandService) {
    this.message = 'PageOneComponent message';
  }
  ngOnInit(): void {
    this.brandService.query({}).subscribe(
      (res: HttpResponse<IBrand[]>) => this.onSuccess(res.body),
      () => this.onError()
    );
  }

  protected onSuccess(data: IBrand[] | null): void {
    this.brands = data || [];
  }

  protected onError(): void {}
}
