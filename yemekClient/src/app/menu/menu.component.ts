import { Component, OnInit } from '@angular/core';
import { YemekService } from '../service/yemek.service';
import { Yemek } from '../beans/yemek';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {
  yemekler: Yemek[] = [];
  seciliYemek: Yemek | null = null;
  form!: FormGroup;

  constructor(
    private yemekService: YemekService,
    private toastr: ToastrService,
    private fb: FormBuilder,
  ) {
    this.form = this.fb.group({
      adi: [''],
      fiyat: [''],
      kota: ['']
    });
  }
  ngOnInit(): void {
    this.yemekService.getirTumYemekler().subscribe({
      next: (yemekler) => {
        this.yemekler = yemekler
      },
      error: (error) => {
        this.toastr.error('Bir hata oluştu');
        console.error(error);
      }
    });
  }
  yemekSec(yemek: Yemek) {
    if (this.seciliYemek == yemek) {
      // seçimi kaldır
      this.seciliYemek = null;
      this.form.reset();
    } else {
      this.seciliYemek = yemek;
      this.form.setValue({ adi: yemek.adi, fiyat: yemek.fiyat, kota: yemek.kota });
    }
  }
  yemekGuncelle() {
    if (this.seciliYemek) {
      const guncelYemek: Yemek = {
        id: this.seciliYemek.id,
        adi: this.form.value.adi,
        fiyat: this.form.value.fiyat,
        kota: this.form.value.kota
      };
      this.yemekService.ekleGuncelle(guncelYemek).subscribe({
        next: () => {
          this.toastr.success('Yemek güncellendi');
          const guncellenecekYemek = this.yemekler.find(y => y.id === this.seciliYemek!.id)!;
          guncellenecekYemek.adi = guncelYemek.adi;
          guncellenecekYemek.fiyat = guncelYemek.fiyat;
          guncellenecekYemek.kota = guncelYemek.kota;
          this.seciliYemek = null;
          this.form.reset();
        },
        error: (error) => {
          this.toastr.error('Bir hata oluştu');
          console.error(error);
        }
      });
    } else {
      this.toastr.error('Seçili yemek yok');
    }
  }
  yemekSil() {
    if (this.seciliYemek) {
      this.yemekService.sil(this.seciliYemek.id).subscribe({
        next: () => {
          this.toastr.success('Yemek silindi');
          for (let i = 0; i < this.yemekler.length; i++) {
            if (this.yemekler[i].id === this.seciliYemek!.id) {
              // i. sıradaki yemeği sil
              this.yemekler.splice(i, 1);
              // break; // burada break kullanmazsanız, silinen yemeklerin sonrasında yeni eklenenler de silinmeyecek
              // bu yüzden break kullandık. yani silinen yemekin sırasını atlayarak döngüden çıkıyoruz.

              // break; // burada da break kullanabilirsiniz. ama sizin durumunuzda break kullanmak daha mantıklı olabilir.

              // return; // bu da mantıklı bir şekilde çalışır. break'un farklı bir kullanımı.
              break;
            }
          }

          this.seciliYemek = null;
          this.form.reset();
        },
        error: (error) => {
          this.toastr.error('Bir hata oluştu');
          console.error(error);
        }
      });
    } else {
      this.toastr.error('Seçili yemek yok');
    }
  }
  formTemizle() {
    this.seciliYemek = null;
    this.form.reset();
  }
  yemekEkle() {
    if (this.form.value.adi == '' || this.form.value.fiyat == '' || this.form.value.kota == '') {
      this.toastr.error('Formu boş bırakmayınız');
      return;
    }
    const guncelYemek: Yemek = {
      id: 0, // yeni eklenen yemek id'si 0 olacak
      adi: this.form.value.adi,
      fiyat: this.form.value.fiyat,
      kota: this.form.value.kota
    };
    this.yemekService.ekleGuncelle(guncelYemek).subscribe({
      next: (eklenenYemek: Yemek) => {
        this.toastr.success('Yemek eklendi');
        this.yemekler.push(eklenenYemek);
        this.seciliYemek = null;
        this.form.reset();
      },
      error: (error) => {
        this.toastr.error('Bir hata oluştu');
        console.error(error);
      }
    });
  }
}
